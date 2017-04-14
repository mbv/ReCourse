package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.StudentReportController;
import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.StudentReportSupplier;
import org.mockito.Mockito;

public class StudentReportControllerTest extends CrudControllerTest<StudentReport, Integer> {
    private StudentReportController studentReportController;
    private StudentReportService studentReportService;
    private StudentReportSupplier studentReportSupplier;

    public StudentReportControllerTest() {
        studentReportService = Mockito.mock(StudentReportService.class);
        studentReportController =  new StudentReportControllerImpl(studentReportService);
        studentReportSupplier = new StudentReportSupplier();
    }

    @Override
    protected CrudController<StudentReport, Integer> getController() {
        return studentReportController;
    }

    @Override
    protected CrudService<StudentReport, Integer> getService() {
        return studentReportService;
    }

    @Override
    protected String getEntityName() {
        return "student/report";
    }

    @Override
    protected EntitySupplier<StudentReport, Integer> getEntitySupplier() {
        return studentReportSupplier;
    }
}
