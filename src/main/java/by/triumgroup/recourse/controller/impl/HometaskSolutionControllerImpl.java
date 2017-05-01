package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static by.triumgroup.recourse.util.Util.allItemsPage;
import static org.slf4j.LoggerFactory.getLogger;

public class HometaskSolutionControllerImpl
        extends AbstractCrudController<HometaskSolution, Integer>
        implements HometaskSolutionController {

    private static final Logger logger = getLogger(HometaskSolutionControllerImpl.class);
    private final MarkService markService;
    private final LessonService lessonService;
    private HometaskSolutionService hometaskSolutionService;

    public HometaskSolutionControllerImpl(HometaskSolutionService hometaskSolutionService,
                                          MarkService markService,
                                          LessonService lessonService) {
        super(hometaskSolutionService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
        this.markService = markService;
        this.lessonService = lessonService;
    }

    @Override
    protected void validateNestedEntities(HometaskSolution entity) {
        if (entity.getStudent().getId() == null) {
            throw new BadRequestException("student.id", "Student ID is not specified");
        }
    }

    @Override
    public Iterable<HometaskSolution> getAll(@Auth UserAuthDetails authDetails) {
        Iterable<HometaskSolution> result;
        if (!authDetails.isAdmin()) {
            if (authDetails.getRole() == User.Role.STUDENT) {
                result = wrapServiceCall(logger, () -> {
                    Optional<List<HometaskSolution>> hometaskSolutions = hometaskSolutionService
                            .findByStudentId(authDetails.getId(), allItemsPage());
                    return hometaskSolutions.orElseThrow(NotFoundException::new);
                });
            } else {
                throw new AccessDeniedException();
            }
        } else {
            result = super.getAll(authDetails);
        }
        return result;
    }

    @Override
    public Mark getMark(@PathVariable("solutionId") Integer solutionId) {
        return wrapServiceCall(logger, () -> {
            Optional<Mark> callResult = markService.findBySolutionId(solutionId);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<HometaskSolution> getStudentSolutions(
            @PathVariable("studentId") Integer studentId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            if (authDetails.isAdmin() || studentId.equals(authDetails.getId())) {
                Optional<List<HometaskSolution>> solutions = hometaskSolutionService.findByStudentId(studentId, pageable);
                return solutions.orElseThrow(NotFoundException::new);
            } else {
                throw new AccessDeniedException();
            }

        });
    }

    @Override
    public HometaskSolution getStudentSolution(
            @PathVariable("studentId") Integer studentId,
            @RequestParam(value = "lessonId") Integer lessonId,
            @Auth UserAuthDetails authDetails) {
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> callResult = hometaskSolutionService.findByStudentIdAndLessonId(studentId, lessonId);
            if (callResult.isPresent()) {
                HometaskSolution hometaskSolution = callResult.get();
                checkAuthority(hometaskSolution, authDetails, this::hasAuthorityToRead);
                return hometaskSolution;
            } else {
                throw new NotFoundException();
            }
        });
    }

    @Override
    public List<HometaskSolution> getLessonSolutions(
            @PathVariable("lessonId") Integer lessonId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<Lesson> lesson = lessonService.findById(lessonId);
            Optional<List<HometaskSolution>> solutions;
            if (lesson.isPresent()) {
                if (authDetails.isAdmin() || lesson.get().getTeacher().getId().equals(authDetails.getId())) {
                    solutions = hometaskSolutionService.findByLessonId(lessonId, pageable);
                } else {
                    throw new AccessDeniedException();
                }
            } else {
                solutions = Optional.empty();
            }
            return solutions.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    protected boolean hasAuthorityToEdit(HometaskSolution entity, UserAuthDetails authDetails) {
        return authDetails.isAdmin() || Objects.equals(entity.getStudent().getId(), authDetails.getId());
    }

    @Override
    protected boolean hasAuthorityToRead(HometaskSolution entity, UserAuthDetails authDetails) {
        boolean result = hasAuthorityToEdit(entity, authDetails);
        if (!result) {
            Optional<Lesson> lesson = lessonService.findById(entity.getLessonId());
            if (lesson.isPresent()) {
                Integer teacherId = lesson.get().getTeacher().getId();
                result = teacherId.equals(authDetails.getId());
            }
        }
        return result;
    }
}
