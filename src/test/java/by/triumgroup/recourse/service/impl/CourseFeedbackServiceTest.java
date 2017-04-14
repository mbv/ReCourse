package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
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

public class CourseFeedbackServiceTest extends CrudServiceTest<CourseFeedback, Integer> {
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackRepository courseFeedbackRepository;
    private CourseFeedbackSupplier courseFeedbackSupplier;
    private CourseRepository courseRepository;

    public CourseFeedbackServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseFeedbackRepository = Mockito.mock(CourseFeedbackRepository.class);
        courseFeedbackService = new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
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
}
