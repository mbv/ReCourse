package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.service.LessonService;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class LessonControllerImpl
        extends AbstractCrudController<Lesson, Integer>
        implements LessonController {

    private static final Logger logger = getLogger(LessonControllerImpl.class);

    public LessonControllerImpl(LessonService lessonService) {
        super(lessonService, logger);
    }

    @Override
    protected boolean hasAuthorityToEdit(Lesson entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getTeacher().getId(), authDetails.getId());
    }
}
