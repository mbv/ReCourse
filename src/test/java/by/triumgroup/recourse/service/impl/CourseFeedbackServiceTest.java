package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CourseFeedbackServiceTest extends CrudServiceTest<CourseFeedback, Integer> {
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackRepository courseFeedbackRepository;
    private CourseFeedbackSupplier courseFeedbackSupplier;
    private CourseSupplier courseSupplier;
    private CourseRepository courseRepository;
    private UserSupplier userSupplier;

    public CourseFeedbackServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseFeedbackRepository = Mockito.mock(CourseFeedbackRepository.class);
        courseFeedbackService = new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository, userRepository);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
        userSupplier = new UserSupplier();
        courseSupplier = new CourseSupplier();
    }

    @Test
    public void findByExistingCourseIdTest() throws Exception {
        Integer id = courseFeedbackSupplier.getAnyId();
        when(courseRepository.exists(id)).thenReturn(true);
        when(courseFeedbackRepository.findByCourseIdOrderByIdDesc(eq(id), Matchers.any()))
                .thenReturn(Collections.singletonList(courseFeedbackSupplier.getValidEntityWithId()));

        Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(id, null);

        verify(courseFeedbackRepository, times(1)).findByCourseIdOrderByIdDesc(eq(id), any());
        verify(courseRepository, times(1)).exists(id);
        assertTrue(feedbacks.isPresent());
    }

    @Test
    public void findByNotExistingCourseIdTest() throws Exception {
        Integer id = courseFeedbackSupplier.getAnyId();
        when(courseRepository.exists(id)).thenReturn(false);

        Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(id, null);

        verify(courseRepository, times(1)).exists(id);
        assertFalse(feedbacks.isPresent());
    }

    @Override
    protected CrudService<CourseFeedback, Integer> getCrudService() {
        return courseFeedbackService;
    }

    @Override
    protected CrudRepository<CourseFeedback, Integer> getCrudRepository() {
        return courseFeedbackRepository;
    }

    @Override
    protected EntitySupplier<CourseFeedback, Integer> getEntitySupplier() {
        return courseFeedbackSupplier;
    }

    @Override
    public void addValidEntityTest() throws Exception {
        CourseFeedback expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        Course course = courseSupplier.getValidEntityWithId();
        course.setStatus(Course.Status.FINISHED);
        course.setId(expectedEntity.getCourseId());
        when(courseRepository.findOne(expectedEntity.getCourseId())).thenReturn(course);
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);
        setupAllowedRoles(expectedEntity);

        Optional<CourseFeedback> actualResult = getCrudService().add(expectedEntity);

        verify(getCrudRepository(), times(1)).save(expectedEntity);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Override
    public void addEntityWithExistingIdTest() throws Exception {
        CourseFeedback entity = getEntitySupplier().getValidEntityWithId();
        Course course = courseSupplier.getValidEntityWithId();
        course.setStatus(Course.Status.FINISHED);
        course.setId(entity.getCourseId());
        when(courseRepository.findOne(entity.getCourseId())).thenReturn(course);
        when(getCrudRepository().save(entity)).thenReturn(entity);
        setupAllowedRoles(entity);

        getCrudService().add(entity);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(entity);
        Assert.assertNull(captor.getValue().getId());
    }

    @Override
    protected void setupAllowedRoles(CourseFeedback entity) {
        Integer studentId = entity.getStudent().getId();
        when(userRepository.findOne(studentId)).thenReturn(userSupplier.getWithRole(User.Role.STUDENT));
    }

}
