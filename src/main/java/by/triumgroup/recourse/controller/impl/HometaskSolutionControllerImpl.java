package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class HometaskSolutionControllerImpl
        extends AbstractCrudController<HometaskSolution, Integer>
        implements HometaskSolutionController {

    private static final Logger logger = getLogger(HometaskSolutionControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;
    private final MarkService markService;
    private final LessonService lessonService;

    public HometaskSolutionControllerImpl(HometaskSolutionService hometaskSolutionService,
                                          MarkService markService,
                                          LessonService lessonService) {
        super(hometaskSolutionService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
        this.markService = markService;
        this.lessonService = lessonService;
    }

    @Override
    public Iterable<HometaskSolution> getAll(@Auth UserAuthDetails authDetails) {
        Iterable<HometaskSolution> result;
        if (!authDetails.isAdmin()) {
            if (authDetails.getRole() == User.Role.STUDENT) {
                result = wrapServiceCall(logger, () -> {
                    Optional<List<HometaskSolution>> hometaskSolutions = hometaskSolutionService
                            .findByStudentId(authDetails.getId(), new PageRequest(0, Integer.MAX_VALUE));
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
    protected boolean hasAuthorityToEdit(HometaskSolution entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getStudent().getId(), authDetails.getId());
    }

    @Override
    protected boolean hasAuthorityToRead(HometaskSolution entity, UserAuthDetails authDetails) {
        boolean result = hasAuthorityToEdit(entity, authDetails);
        if (!result){
            Optional<Lesson> lesson = lessonService.findById(entity.getLessonId());
            if (lesson.isPresent()){
                Integer teacherId = lesson.get().getTeacher().getId();
                result = teacherId.equals(authDetails.getId());
            }
        }
        return result;
    }
}
