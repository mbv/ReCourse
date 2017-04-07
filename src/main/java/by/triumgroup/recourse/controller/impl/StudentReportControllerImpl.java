package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.StudentReportController;
import by.triumgroup.recourse.entity.StudentReport;
import by.triumgroup.recourse.service.StudentReportService;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class StudentReportControllerImpl
        extends AbstractCrudController<StudentReport, Integer>
        implements StudentReportController {

    private static final Logger logger = getLogger(StudentReportControllerImpl.class);

    public StudentReportControllerImpl(StudentReportService studentReportService) {
        super(studentReportService, logger);
    }
}
