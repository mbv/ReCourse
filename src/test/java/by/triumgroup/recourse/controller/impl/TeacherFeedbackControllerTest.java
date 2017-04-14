package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.TeacherFeedbackController;
import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.TeacherFeedbackSupplier;
import org.mockito.Mockito;

public class TeacherFeedbackControllerTest extends CrudControllerTest<TeacherFeedback, Integer> {
    private TeacherFeedbackController teacherFeedbackController;
    private TeacherFeedbackService teacherFeedbackService;
    private TeacherFeedbackSupplier teacherFeedbackSupplier;

    public TeacherFeedbackControllerTest() {
        teacherFeedbackService = Mockito.mock(TeacherFeedbackService.class);
        teacherFeedbackController = new TeacherFeedbackControllerImpl(teacherFeedbackService);
        teacherFeedbackSupplier = new TeacherFeedbackSupplier();
    }

    @Override
    protected CrudController<TeacherFeedback, Integer> getController() {
        return teacherFeedbackController;
    }

    @Override
    protected CrudService<TeacherFeedback, Integer> getService() {
        return teacherFeedbackService;
    }

    @Override
    protected String getEntityName() {
        return "teacher/feedback";
    }

    @Override
    protected EntitySupplier<TeacherFeedback, Integer> getEntitySupplier() {
        return teacherFeedbackSupplier;
    }
}
