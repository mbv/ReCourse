package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseFeedbackController;
import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import org.mockito.Mockito;

public class CourseFeedbackControllerTest extends CrudControllerTest<CourseFeedback, Integer> {

    private CourseFeedbackController courseFeedbackController;
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackSupplier courseFeedbackSupplier;

    public CourseFeedbackControllerTest() {
        courseFeedbackService = Mockito.mock(CourseFeedbackService.class);
        courseFeedbackController = new CourseFeedbackControllerImpl(courseFeedbackService);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
    }

    @Override
    protected CrudController<CourseFeedback, Integer> getController() {
        return courseFeedbackController;
    }

    @Override
    protected CrudService<CourseFeedback, Integer> getService() {
        return courseFeedbackService;
    }

    @Override
    protected String getEntityName() {
        return "course/feedback";
    }

    @Override
    protected EntitySupplier<CourseFeedback, Integer> getEntitySupplier() {
        return courseFeedbackSupplier;
    }
}
