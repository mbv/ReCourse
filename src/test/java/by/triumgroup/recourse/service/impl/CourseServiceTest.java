package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CourseServiceTest extends CrudServiceTest<Course, Integer> {
    private CourseService courseService;
    private CourseRepository courseRepository;
    private CourseSupplier courseSupplier;

    public CourseServiceTest() {
        userRepository = Mockito.mock(UserRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);
        courseSupplier = new CourseSupplier();
    }

    @Override
    protected CrudService<Course, Integer> getCrudService() {
        return courseService;
    }

    @Override
    protected CrudRepository<Course, Integer> getCrudRepository() {
        return courseRepository;
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }

    @Test
    public void searchByValidTitleTest() throws Exception {
        when(courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(anyString(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        List<Course> courses = courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc("test", null);

        verify(courseRepository, times(1))
                .findByTitleContainingIgnoreCaseOrderByIdDesc(anyString(), any());
        assertEquals(1, courses.size());
    }

    @Test
    public void findByStatusTest() throws Exception {
        when(courseRepository.findByStatusOrderByIdDesc(any(), any()))
                .thenReturn(Collections.emptyList());

        courseService.findByStatus(Course.Status.FINISHED, null);

        verify(courseRepository, times(1))
                .findByStatusOrderByIdDesc(any(), any());
    }

    @Override
    protected void setupAllowedRoles(Course entity) { }

    @Override
    protected void setupForbiddenRoles(Course entity) { }
}
