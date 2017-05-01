package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class LessonServiceTest extends CrudServiceTest<Lesson, Integer> {
    private LessonRepository lessonRepository;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private UserSupplier userSupplier;
    private LessonTimeValidator lessonTimeValidator;

    public LessonServiceTest() {
        lessonRepository = Mockito.mock(LessonRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        lessonTimeValidator = Mockito.mock(LessonTimeValidator.class);
        when(lessonTimeValidator.supports(any())).thenCallRealMethod();
        lessonService = new LessonServiceImpl(lessonRepository, courseRepository, userRepository, lessonTimeValidator);
        lessonSupplier = new LessonSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void findByExistingCourseId() throws Exception {
        when(courseRepository.exists(any())).thenReturn(true);
        when(lessonRepository.findByCourseIdOrderByStartTimeAsc(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<Lesson>> result = lessonService.findByCourseId(lessonSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(lessonRepository, times(1)).findByCourseIdOrderByStartTimeAsc(any(), any());
        assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingCourseId() throws Exception {
        when(courseRepository.exists(any())).thenReturn(false);

        Optional<List<Lesson>> result = lessonService.findByCourseId(lessonSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(lessonRepository, times(0)).findByCourseIdOrderByStartTimeAsc(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByExistingTeacherId() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(lessonRepository.findByTeacherIdOrderByStartTimeAsc(any(), any())).thenReturn(Lists.emptyList());
        when(userRepository.findOne(any())).thenReturn(teacher);

        Optional<List<Lesson>> hometask = lessonService.findByTeacherId(userSupplier.getAnyId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(1)).findByTeacherIdOrderByStartTimeAsc(any(), any());
        Assert.assertTrue(hometask.isPresent());
    }

    @Test
    public void findByNotExistingLessonId() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<Lesson>> hometask = lessonService.findByTeacherId(userSupplier.getAnyId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(0)).findByTeacherIdOrderByStartTimeAsc(any(), any());
        Assert.assertFalse(hometask.isPresent());
    }


    @Test
    public void findByTeacherAndCourseId() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(courseRepository.exists(any())).thenReturn(true);
        when(userRepository.findOne(any())).thenReturn(teacher);
        when(lessonRepository.findByTeacherIdAndCourseIdOrderByStartTimeAsc(any(), any(), any())).thenReturn(Lists.emptyList());

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(userRepository, times(1)).findOne(any());
        verify(lessonRepository, times(1)).findByTeacherIdAndCourseIdOrderByStartTimeAsc(any(), any(), any());
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
        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeAsc(any(), any(), any());
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
        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeAsc(any(), any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByNotExistingTeacherAndNotExistingCourseId() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(courseRepository.exists(any())).thenReturn(false);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<Lesson>> result = lessonService.findByTeacherIdAndCourseId(userSupplier.getAnyId(), userSupplier.getAnyId(), null);

        verify(lessonRepository, times(0)).findByTeacherIdAndCourseIdOrderByStartTimeAsc(any(), any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void updateLessonWithInvalidTimeTest() throws Exception {
        Lesson lesson = lessonSupplier.getValidEntityWithoutId();
        Integer parameterId = lessonSupplier.getAnyId();
        doAnswer(invocationOnMock -> {
            Errors errors = (Errors)invocationOnMock.getArguments()[1];
            errors.rejectValue("", "");
            return null;
        }).when(lessonTimeValidator).validate(any(), any());
        when(lessonRepository.exists(parameterId)).thenReturn(true);
        when(lessonRepository.findOne(parameterId)).thenReturn(lesson);
        setupAllowedRoles(lesson);

        thrown.expect(ServiceBadRequestException.class);

        lessonService.update(lesson, parameterId, User.Role.ADMIN);

        verify(lessonRepository, times(0)).save(lesson);
    }

    @Override
    public void updateEntityWithoutIdTest() throws Exception {
        Lesson newEntity = getEntitySupplier().getValidEntityWithoutId();
        Lesson databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        databaseEntity.setId(parameterId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<Lesson> actualResult = lessonService.update(newEntity, parameterId, User.Role.ADMIN);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<Lesson>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Override
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<Integer, Integer> ids = getEntitySupplier().getDifferentIds();
        Integer entityId = ids.getFirst();
        Integer parameterId = ids.getSecond();
        Lesson newEntity = getEntitySupplier().getValidEntityWithoutId();
        Lesson databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        databaseEntity.setId(parameterId);
        newEntity.setId(entityId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<Lesson> actualResult = lessonService.update(newEntity, parameterId, User.Role.ADMIN);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<Lesson>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        Lesson entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().exists(parameterId)).thenReturn(false);
        when(getCrudRepository().findOne(parameterId)).thenReturn(null);

        Optional<Lesson> actualResult = lessonService.update(entity, parameterId, User.Role.ADMIN);

        verify(getCrudRepository(), times(0)).save(entity);
        Assert.assertFalse(actualResult.isPresent());
    }

    @Override
    public void updateEntityExceptionTest() throws Exception {
        Lesson entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(Matchers.<Lesson>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(entity);
        setupAllowedRoles(entity);

        thrown.expect(ServiceException.class);

        lessonService.update(entity, parameterId, User.Role.ADMIN);

        verify(getCrudRepository(), times(1)).save(Matchers.<Lesson>any());
    }

    @Test
    public void addLessonWithInvalidTimeTest() throws Exception {
        Lesson lesson = lessonSupplier.getValidEntityWithId();
        doAnswer(invocationOnMock -> {
            Errors errors = (Errors)invocationOnMock.getArguments()[1];
            errors.rejectValue("", "");
            return null;
        }).when(lessonTimeValidator).validate(any(), any());
        setupAllowedRoles(lesson);

        thrown.expect(ServiceBadRequestException.class);

        lessonService.add(lesson);

        verify(lessonRepository, times(0)).save(lesson);
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

    @Override
    protected void setupAllowedRoles(Lesson entity) {
        Integer teacherId = entity.getTeacher().getId();
        when(userRepository.findOne(teacherId)).thenReturn(userSupplier.getWithRole(User.Role.TEACHER));
    }
}
