package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.validator.CourseTeacherValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Pair;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static by.triumgroup.recourse.util.TestUtil.createValidationErrors;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CourseTeacherValidatorTest {
    @InjectMocks
    private CourseTeacherValidator courseTeacherValidator;

    @Mock
    private CourseRepository courseRepository;

    private CourseSupplier courseSupplier;
    private UserSupplier userSupplier;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        courseSupplier = new CourseSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void updatingCourseWithSameTeacher() throws Exception {
        Integer id = courseSupplier.getAnyId();
        Course oldCourse = courseSupplier.getValidEntityWithoutId();
        oldCourse.setId(id);
        Course newCourse = courseSupplier.getValidEntityWithoutId();
        newCourse.setId(id);
        newCourse.setTeacher(oldCourse.getTeacher());
        when(courseRepository.findOne(oldCourse.getId())).thenReturn(oldCourse);
        Errors errors = createValidationErrors(newCourse, "course");

        courseTeacherValidator.validate(newCourse, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(courseRepository, times(0)).canUpdateTeacher(any(), any());
        verify(courseRepository, times(0)).updateTeacher(any(), any());
        verify(courseRepository, times(1)).findOne(id);
    }

    @Test
    public void updatingTeacherWithFreeTime() throws Exception {
        Integer id = courseSupplier.getAnyId();
        Course newCourse = courseSupplier.getValidEntityWithoutId();
        Course oldCourse = courseSupplier.getValidEntityWithoutId();
        newCourse.setId(id);
        oldCourse.setId(id);

        Pair<Integer, Integer> teachersIds = userSupplier.getDifferentIds();
        User oldTeacher = userSupplier.getWithRole(User.Role.TEACHER);
        oldTeacher.setId(teachersIds.getFirst());
        oldCourse.setTeacher(oldTeacher);
        User newTeacher = userSupplier.getWithRole(User.Role.TEACHER);
        newTeacher.setId(teachersIds.getSecond());
        newCourse.setTeacher(newTeacher);

        when(courseRepository.findOne(id)).thenReturn(oldCourse);
        when(courseRepository.canUpdateTeacher(newCourse.getId(), newTeacher.getId())).thenReturn(true);
        Errors errors = createValidationErrors(newCourse, "course");

        courseTeacherValidator.validate(newCourse, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(courseRepository, times(1)).canUpdateTeacher(any(), any());
        verify(courseRepository, times(1)).findOne(id);
    }

    @Test
    public void updatingTeacherWithBusyTime() throws Exception {
        Integer id = courseSupplier.getAnyId();
        Course newCourse = courseSupplier.getValidEntityWithoutId();
        Course oldCourse = courseSupplier.getValidEntityWithoutId();
        newCourse.setId(id);
        oldCourse.setId(id);

        Pair<Integer, Integer> teachersIds = userSupplier.getDifferentIds();
        User oldTeacher = userSupplier.getWithRole(User.Role.TEACHER);
        oldTeacher.setId(teachersIds.getFirst());
        oldCourse.setTeacher(oldTeacher);
        User newTeacher = userSupplier.getWithRole(User.Role.TEACHER);
        newTeacher.setId(teachersIds.getSecond());
        newCourse.setTeacher(newTeacher);

        when(courseRepository.findOne(id)).thenReturn(oldCourse);
        when(courseRepository.canUpdateTeacher(newCourse.getId(), newTeacher.getId())).thenReturn(false);
        Errors errors = createValidationErrors(newCourse, "course");

        courseTeacherValidator.validate(newCourse, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("teacher", fieldError.getField());
        verify(courseRepository, Mockito.times(1)).findOne(newCourse.getId());
    }
}
