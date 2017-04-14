package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.TeacherFeedbackController;
import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class TeacherFeedbackControllerImpl
        extends AbstractCrudController<TeacherFeedback, Integer>
        implements TeacherFeedbackController {

    private static final Logger logger = getLogger(TeacherFeedbackControllerImpl.class);

    public TeacherFeedbackControllerImpl(TeacherFeedbackService teacherFeedbackService) {
        super(teacherFeedbackService, logger);
    }

    @Override
    protected boolean hasAuthorityToPerform(TeacherFeedback entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getStudent().getId(), authDetails.getId());
    }
}
