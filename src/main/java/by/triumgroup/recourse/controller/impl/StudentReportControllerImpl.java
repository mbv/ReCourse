package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.StudentReportController;
import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.service.StudentReportService;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class StudentReportControllerImpl
        extends AbstractCrudController<StudentReport, Integer>
        implements StudentReportController {

    private static final Logger logger = getLogger(StudentReportControllerImpl.class);

    public StudentReportControllerImpl(StudentReportService studentReportService) {
        super(studentReportService, logger);
    }

    @Override
    protected boolean hasAuthorityToPerform(StudentReport entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getTeacher().getId(), authDetails.getId());
    }
}
