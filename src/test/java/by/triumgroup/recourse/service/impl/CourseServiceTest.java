package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
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
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CourseServiceTest extends CrudServiceTest<Course, Integer> {
    private UserRepository userRepository;
    private CourseService courseService;
    private CourseRepository courseRepository;
    private CourseSupplier courseSupplier;
    private UserSupplier userSupplier;

    public CourseServiceTest() {
        userRepository = Mockito.mock(UserRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository, userRepository);
        courseSupplier = new CourseSupplier();
        userSupplier = new UserSupplier();
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
    public void findByNotExistingTeacherIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.STUDENT);
        when(userRepository.findOne(anyInt())).thenReturn(user);

        Optional<List<Course>> courses = courseService.findByTeacherId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingTeacherIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.TEACHER);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(courseRepository.findByTeacherIdOrderByIdDesc(anyInt(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        Optional<List<Course>> courses = courseService.findByTeacherId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(courseRepository, times(1)).findByTeacherIdOrderByIdDesc(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByExistingTeacherIdAndStatus() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.TEACHER);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(courseRepository.findByTeacherIdAndStatusOrderByIdDesc(anyInt(), any(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        Optional<List<Course>> courses =
                courseService.findByTeacherIdAndStatus(user.getId(), Course.Status.ONGOING, null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(courseRepository, times(1))
                .findByTeacherIdAndStatusOrderByIdDesc(anyInt(), any(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotExistingOrganizerIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.TEACHER);
        when(userRepository.findOne(anyInt())).thenReturn(user);

        Optional<List<Course>> courses = courseService.findByOrganizerId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingOrganizerIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.ORGANIZER);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(courseRepository.findByOrganizerIdOrderByIdDesc(anyInt(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        Optional<List<Course>> courses = courseService.findByOrganizerId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(courseRepository, times(1)).findByOrganizerIdOrderByIdDesc(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByExistingOrganizerIdAndStatus() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.ORGANIZER);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(courseRepository.findByOrganizerIdAndStatusOrderByIdDesc(anyInt(), any(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        Optional<List<Course>> courses =
                courseService.findByOrganizerIdAndStatus(user.getId(), Course.Status.ONGOING, null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(courseRepository, times(1))
                .findByOrganizerIdAndStatusOrderByIdDesc(anyInt(), any(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByStatusTest() throws Exception {
        when(courseRepository.findByStatusOrderByIdDesc(any(), any()))
                .thenReturn(Collections.emptyList());

        courseService.findByStatus(Course.Status.FINISHED, null);

        verify(courseRepository, times(1))
                .findByStatusOrderByIdDesc(any(), any());
    }
}
