package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourseControllerTest extends CrudControllerTest<Course, Integer> {
    private static final String COURSE_ID_REQUEST = "/api/courses/1/{param}";
    private static final String COURSE_SEARCH_REQUEST = "/api/courses/search?{name}={value}";
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;
    private final CourseController courseController;
    private final CourseSupplier courseSupplier;

    public CourseControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        lessonService = Mockito.mock(LessonService.class);
        courseFeedbackService = Mockito.mock(CourseFeedbackService.class);
        courseController = new CourseControllerImpl(courseService, lessonService, courseFeedbackService);
        courseSupplier = new CourseSupplier();
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
    public void searchByTitleTest() throws Exception {
        when(courseService.searchByTitle(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(COURSE_SEARCH_REQUEST, "title", "title")
                .andExpect(status().isOk());
    }

    @Test
    public void searchByStatusTest() throws Exception {
        when(courseService.findByStatus(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
        sendGet(COURSE_SEARCH_REQUEST, "status", Course.Status.DRAFT)
                .andExpect(status().isOk());
    }

    @Test
    public void searchByInvalidStatusTest() throws Exception {
        when(courseService.findByStatus(any(), any())).thenReturn(Optional.of(Lists.emptyList()));
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
        return "courses";
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(Course entity, User validUserWithId) {
        validUserWithId.setRole(User.Role.ADMIN);
        return validUserWithId;
    }
}
