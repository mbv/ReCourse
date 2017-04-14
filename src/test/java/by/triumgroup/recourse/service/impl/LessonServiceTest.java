package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LessonServiceTest extends CrudServiceTest<Lesson, Integer> {
    private LessonRepository lessonRepository;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private UserSupplier userSupplier;

    public LessonServiceTest() {
        lessonRepository = Mockito.mock(LessonRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        lessonService = new LessonServiceImpl(lessonRepository, courseRepository, userRepository);
        lessonSupplier = new LessonSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void findByExistingCourseId() throws Exception {
        when(courseRepository.exists(any())).thenReturn(true);
        when(lessonRepository.findByCourseIdOrderByStartTimeDesc(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<Lesson>> result = lessonService.findByCourseId(lessonSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(lessonRepository, times(1)).findByCourseIdOrderByStartTimeDesc(any(), any());
        assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingCourseId() throws Exception {
        when(courseRepository.exists(any())).thenReturn(false);

        Optional<List<Lesson>> result = lessonService.findByCourseId(lessonSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(lessonRepository, times(0)).findByCourseIdOrderByStartTimeDesc(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByExistingTeacherId() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(lessonRepository.findByTeacherIdOrderByStartTimeDesc(any(), any())).thenReturn(Lists.emptyList());
        when(userRepository.findOne(any())).thenReturn(teacher);

        Optional<List<Lesson>> hometask = lessonService.findByTeacherId(userSupplier.getAnyId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(1)).findByTeacherIdOrderByStartTimeDesc(any(), any());
        Assert.assertTrue(hometask.isPresent());
    }

    @Test
    public void findByNotExistingLessonId() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<Lesson>> hometask = lessonService.findByTeacherId(userSupplier.getAnyId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(0)).findByTeacherIdOrderByStartTimeDesc(any(), any());
        Assert.assertFalse(hometask.isPresent());
    }


    @Test
    public void findByTeacherAndCourseId() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(courseRepository.exists(any())).thenReturn(true);
        when(userRepository.findOne(any())).thenReturn(teacher);
        when(lessonRepository.findByTeacherIdAndCourseIdOrderByStartTimeDesc(any(), any(), any())).thenReturn(Lists.emptyList());

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(1)).findByTeacherIdAndCourseIdOrderByStartTimeDesc(any(), any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingTeacherAndCourseId() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(courseRepository.exists(any())).thenReturn(true);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeDesc(any(), any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByTeacherAndNotExistingCourseId() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(courseRepository.exists(any())).thenReturn(false);
        when(userRepository.findOne(any())).thenReturn(teacher);

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeDesc(any(), any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByNotExistingTeacherAndNotExistingCourseId() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(courseRepository.exists(any())).thenReturn(false);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeDesc(any(), any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Override
    protected CrudService<Lesson, Integer> getCrudService() {
        return lessonService;
    }

    @Override
    protected CrudRepository<Lesson, Integer> getCrudRepository() {
        return lessonRepository;
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }
}
