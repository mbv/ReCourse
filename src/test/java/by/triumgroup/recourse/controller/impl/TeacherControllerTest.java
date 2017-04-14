package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractControllerTest;
import by.triumgroup.recourse.controller.TeacherController;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TeacherControllerTest extends AbstractControllerTest {
    private static final String TEACHER_ID_REQUEST = "/teacher/1/{param}";
    private static final String TEACHER_ID_REQUEST_PARAMS = "/teacher/1/{param}?{name}={value}";
    private CourseService courseService;
    private LessonService lessonService;
    private StudentReportService studentReportService;
    private TeacherFeedbackService teacherFeedbackService;
    private TeacherController teacherController;

    public TeacherControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        lessonService = Mockito.mock(LessonService.class);
        studentReportService = Mockito.mock(StudentReportService.class);
        teacherFeedbackService = Mockito.mock(TeacherFeedbackService.class);
        teacherController = new TeacherControllerImpl(courseService, lessonService, studentReportService, teacherFeedbackService);
    }

    @Test
    public void getCoursesExistingTeacherTest() throws Exception {
        when(courseService.findByTeacherId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST, "courses")
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesNotExistingTeacherTest() throws Exception {
        when(courseService.findByTeacherId(any(), any())).thenReturn(Optional.empty());
        sendGet(TEACHER_ID_REQUEST, "courses")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCoursesByStatusExistingTeacherTest() throws Exception {
        when(courseService.findByTeacherIdAndStatus(any(), any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST_PARAMS, "courses", "status", Course.Status.ONGOING)
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesInvalidStatusTest() throws Exception {
        when(courseService.findByTeacherId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST_PARAMS, "courses", "status", "NOT_A_VALID_STATUS")
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getLessonsExistingTeacherTest() throws Exception {
        when(lessonService.findByTeacherId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST, "lessons")
                .andExpect(status().isOk());
    }

    @Test
    public void getLessonsNotExistingTeacherTest() throws Exception {
        when(lessonService.findByTeacherId(any(), any())).thenReturn(Optional.empty());
        sendGet(TEACHER_ID_REQUEST, "lessons")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getLessonsByCourseTest() throws Exception {
        when(lessonService.findByTeacherIdAndCourseId(any(), any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST_PARAMS, "lessons", "courseId", 1)
                .andExpect(status().isOk());
    }

    @Test
    public void getReportsExistingTeacherTest() throws Exception {
        when(studentReportService.findByTeacherId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST, "reports")
                .andExpect(status().isOk());
    }

    @Test
    public void getReportsNotExistingTeacherTest() throws Exception {
        when(studentReportService.findByTeacherId(any(), any())).thenReturn(Optional.empty());
        sendGet(TEACHER_ID_REQUEST, "reports")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFeedbacksExistingTeacherTest() throws Exception {
        when(teacherFeedbackService.findByTeacherId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(TEACHER_ID_REQUEST, "feedbacks")
                .andExpect(status().isOk());
    }

    @Test
    public void getFeedbacksNotExistingTeacherTest() throws Exception {
        when(teacherFeedbackService.findByTeacherId(any(), any())).thenReturn(Optional.empty());
        sendGet(TEACHER_ID_REQUEST, "feedbacks")
                .andExpect(status().isNotFound());
    }

    @Override
    protected Object getController() {
        return teacherController;
    }
}
