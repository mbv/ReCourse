package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.StudentController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.HometaskSolution;
import by.triumgroup.recourse.entity.StudentReport;
import by.triumgroup.recourse.entity.TeacherFeedback;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class StudentControllerImpl implements StudentController {

    private static final Logger logger = getLogger(StudentControllerImpl.class);
    private final StudentReportService studentReportService;
    private final TeacherFeedbackService teacherFeedbackService;
    private final HometaskSolutionService hometaskSolutionService;

    public StudentControllerImpl(
            StudentReportService studentReportService,
            TeacherFeedbackService teacherFeedbackService,
            HometaskSolutionService hometaskSolutionService
    ) {
        this.studentReportService = studentReportService;
        this.teacherFeedbackService = teacherFeedbackService;
        this.hometaskSolutionService = hometaskSolutionService;
    }

    @Override
    public List<StudentReport> getReports(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> studentReportService.findByStudentId(studentId, pageable));
    }

    @Override
    public List<TeacherFeedback> getTeacherFeedbacks(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> teacherFeedbackService.findByStudentId(studentId, pageable));
    }

    @Override
    public List<HometaskSolution> getSolutions(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> hometaskSolutionService.findByStudentId(studentId, pageable));
    }

    @Override
    public HometaskSolution getSolutionForLesson(
            @PathVariable("studentId") Integer studentId,
            @RequestParam("hometaskId") Integer hometaskId,
            Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<HometaskSolution> callResult =
                    hometaskSolutionService.findByStudentIdAndHometaskId(studentId, hometaskId);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }
}
