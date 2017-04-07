package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseFeedbackController;
import by.triumgroup.recourse.entity.CourseFeedback;
import by.triumgroup.recourse.service.CourseFeedbackService;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class CourseFeedbackControllerImpl
        extends AbstractCrudController<CourseFeedback, Integer>
        implements CourseFeedbackController {

    private static final Logger logger = getLogger(CourseFeedbackControllerImpl.class);

    public CourseFeedbackControllerImpl(CourseFeedbackService courseFeedbackService) {
        super(courseFeedbackService, logger);
    }
}
