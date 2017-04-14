package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourseControllerTest extends CrudControllerTest<Course, Integer> {
    private static final String COURSE_ID_REQUEST = "/course/1/{param}";
    private static final String COURSE_SEARCH_REQUEST = "/course/search?{name}={value}";
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;
    private final StudentReportService studentReportService;
    private final CourseController courseController;
    private final CourseSupplier courseSupplier;
    private final LessonSupplier lessonSupplier;

    public CourseControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        lessonService = Mockito.mock(LessonService.class);
        courseFeedbackService = Mockito.mock(CourseFeedbackService.class);
        studentReportService = Mockito.mock(StudentReportService.class);
        courseController = new CourseControllerImpl(courseService, lessonService, courseFeedbackService, studentReportService);
        courseSupplier = new CourseSupplier();
        lessonSupplier = new LessonSupplier();
    }

    @Test
    public void getLessonsNotExistingCourseTest() throws Exception{
        when(lessonService.findByCourseId(any(), any())).thenReturn(Optional.empty());
        sendGet(COURSE_ID_REQUEST, "lessons")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getLessonsExistingCourseTest() throws Exception{
        when(lessonService.findByCourseId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(COURSE_ID_REQUEST,"lessons")
                .andExpect(status().isOk());
    }

    @Test
    public void getFeedbacksNotExistingCourseTest() throws Exception{
        when(courseFeedbackService.findByCourseId(any(), any())).thenReturn(Optional.empty());
        sendGet(COURSE_ID_REQUEST, "feedbacks")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFeedbacksExistingCourseTest() throws Exception{
        when(courseFeedbackService.findByCourseId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(COURSE_ID_REQUEST, "feedbacks")
                .andExpect(status().isOk());
    }

    @Test
    public void getReportsNotExistingCourseTest() throws Exception{
        when(studentReportService.findByCourseId(any(), any())).thenReturn(Optional.empty());
        sendGet(COURSE_ID_REQUEST, "reports")
                .andExpect(status().isNotFound());
    }

    @Test
    public void getReportsExistingCourseTest() throws Exception{
        when(studentReportService.findByCourseId(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(COURSE_ID_REQUEST, "reports")
                .andExpect(status().isOk());
    }

    @Test
    public void searchByTitleTest() throws Exception {
        when(courseService.searchByTitle(any(), any())).thenReturn(Lists.emptyList());
        sendGet(COURSE_SEARCH_REQUEST, "title", "title")
                .andExpect(status().isOk());
    }

    @Test
    public void searchByStatusTest() throws Exception {
        when(courseService.findByStatus(any(), any())).thenReturn(Lists.emptyList());
        sendGet(COURSE_SEARCH_REQUEST, "status", Course.Status.ONGOING)
                .andExpect(status().isOk());
    }

    @Test
    public void searchByInvalidStatusTest() throws Exception {
        when(courseService.findByStatus(any(), any())).thenReturn(Lists.emptyList());
        sendGet(COURSE_SEARCH_REQUEST, "status", "NOT_A_VALID_STATUS")
                .andExpect(status().isBadRequest());
    }

    @Override
    protected CrudController<Course, Integer> getController() {
        return courseController;
    }

    @Override
    protected CrudService<Course, Integer> getService() {
        return courseService;
    }

    @Override
    protected String getEntityName() {
        return "course";
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }
}
