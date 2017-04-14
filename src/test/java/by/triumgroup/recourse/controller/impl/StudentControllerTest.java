package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractControllerTest;
import by.triumgroup.recourse.controller.StudentController;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest extends AbstractControllerTest {
    private static final String STUDENT_ID_REQUEST = "/student/1/{param}";
    private static final String STUDENT_ID_REQUEST_PARAMS = "/student/1/{param}?{name}={value}";
    private HometaskSolutionService hometaskSolutionService;
    private TeacherFeedbackService teacherFeedbackService;
    private StudentReportService studentReportService;
    private StudentController studentController;
    private HometaskSolutionSupplier hometaskSolutionSupplier;

    public StudentControllerTest() {
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        teacherFeedbackService = Mockito.mock(TeacherFeedbackService.class);
        studentReportService = Mockito.mock(StudentReportService.class);
        studentController = new StudentControllerImpl(studentReportService, teacherFeedbackService, hometaskSolutionService);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
    }

    @Test
    public void getReportsExistingStudentTest() throws Exception {
        when(studentReportService.findByStudentId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(STUDENT_ID_REQUEST, "reports")
                .andExpect(status().isOk());
    }

    @Test
    public void getReportsNotExistingStudentTest() throws Exception {
        when(studentReportService.findByStudentId(any(), any())).thenReturn(Optional.empty());
        sendGet(STUDENT_ID_REQUEST, "reports")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFeedbacksExistingStudentTest() throws Exception {
        when(teacherFeedbackService.findByStudentId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(STUDENT_ID_REQUEST, "feedbacks")
                .andExpect(status().isOk());
    }

    @Test
    public void getFeedbacksNotExistingStudentTest() throws Exception {
        when(teacherFeedbackService.findByStudentId(any(), any())).thenReturn(Optional.empty());
        sendGet(STUDENT_ID_REQUEST, "feedbacks")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSolutionsExistingStudentTest() throws Exception {
        when(hometaskSolutionService.findByStudentId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(STUDENT_ID_REQUEST, "solutions")
                .andExpect(status().isOk());
    }

    @Test
    public void getSolutionsNotExistingStudentTest() throws Exception {
        when(hometaskSolutionService.findByStudentId(any(), any())).thenReturn(Optional.empty());
        sendGet(STUDENT_ID_REQUEST, "solutions")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSolutionForLessonTest() throws Exception {
        when(hometaskSolutionService.findByStudentIdAndHometaskId(any(), any())).thenReturn(Optional.of(hometaskSolutionSupplier.getValidEntityWithId()));
        sendGet(STUDENT_ID_REQUEST_PARAMS, "solution", "hometaskId", 1)
                .andExpect(status().isOk());
    }

    @Test
    public void getSolutionForLessonNotExistingTest() throws Exception {
        when(hometaskSolutionService.findByStudentIdAndHometaskId(any(), any())).thenReturn(Optional.empty());
        sendGet(STUDENT_ID_REQUEST_PARAMS, "solution", "hometaskId", 1)
                .andExpect(status().isNotFound());
    }


    @Override
    protected Object getController() {
        return studentController;
    }
}
