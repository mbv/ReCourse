package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class MarkControllerImpl
        extends AbstractCrudController<Mark, Integer>
        implements MarkController {

    private static final Logger logger = getLogger(MarkControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;
    private final LessonService lessonService;

    public MarkControllerImpl(
            MarkService markService,
            HometaskSolutionService hometaskSolutionService,
            LessonService lessonService
    ) {
        super(markService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
        this.lessonService = lessonService;
    }

    @Override
    public <S extends Mark> S create(@Valid @RequestBody S entity, @Auth UserAuthDetails authDetails) {
        Optional<HometaskSolution> solution = hometaskSolutionService.findById(entity.getSolutionId());
        if (solution.isPresent()) {
            if (solution.get().getMark() != null) {
                throw new BadRequestException();
            }
        }
        return super.create(entity, authDetails);
    }

    @Override
    protected boolean hasAuthorityToEdit(Mark entity, UserAuthDetails authDetails) {
        Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.findById(entity.getSolutionId());
        if (hometaskSolution.isPresent()) {
            Optional<Lesson> lesson = lessonService.findById(hometaskSolution.get().getLessonId());
            if (lesson.isPresent()) {
                return Objects.equals(lesson.get().getTeacher().getId(), authDetails.getId());
            }
        }
        return true; //TODO: why true here?
    }
}
