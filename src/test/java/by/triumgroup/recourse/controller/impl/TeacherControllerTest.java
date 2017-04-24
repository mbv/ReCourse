package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractControllerTest;
import by.triumgroup.recourse.controller.TeacherController;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
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
    private TeacherController teacherController;

    public TeacherControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        lessonService = Mockito.mock(LessonService.class);
        teacherController = new TeacherControllerImpl(courseService, lessonService);
    }

    @Test
    public void getCoursesInvalidStatusTest() throws Exception {
        when(courseService.findByStatus(any(), any())).thenReturn(Lists.emptyList());
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

    @Override
    protected Object getController() {
        return teacherController;
    }
}
