package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.service.UserService;
import org.slf4j.Logger;

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
            LessonService lessonService,
            UserService userService
    ) {
        super(markService, userService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
        this.lessonService = lessonService;
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
        return false;
    }
}
