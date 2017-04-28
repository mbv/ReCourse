package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.Collections;

import static by.triumgroup.recourse.util.TestUtil.createValidationErrors;
import static org.mockito.Mockito.*;

public class UserRoleValidatorTest {
    @Mock
    private UserRepository userRepository;

    private CourseFeedbackSupplier courseFeedbackSupplier;
    private UserSupplier userSupplier;
    private CourseFeedbackRepository courseFeedbackRepository;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
        userSupplier = new UserSupplier();
        courseFeedbackRepository = Mockito.mock(CourseFeedbackRepository.class);
    }

    @Test
    public void supportBaseEntityTest() throws Exception {
        UserRoleValidator<CourseFeedback, Integer> userRoleValidator = getEmptyValidator();
        boolean actualResult = userRoleValidator.supports(new BaseEntity<Integer>() {}.getClass());
        Assert.assertTrue(actualResult);
    }

    @Test
    public void supportNotBaseEntityTest() throws Exception {
        UserRoleValidator<CourseFeedback, Integer> userRoleValidator = getEmptyValidator();
        boolean actualResult = userRoleValidator.supports(Object.class);
        Assert.assertFalse(actualResult);
    }

    @Test
    public void singleRoleSingleFieldSuccessValidationTest() throws Exception {
        User.Role allowedRole = User.Role.STUDENT;
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                allowedRole);
        UserRoleValidator<CourseFeedback, Integer> validator = new UserRoleValidator<>(studentFieldInfo, userRepository, courseFeedbackRepository);

        CourseFeedback entity = courseFeedbackSupplier.getValidEntityWithId();
        User student = userSupplier.getWithRole(allowedRole);
        student.setId(entity.getStudent().getId());

        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(student);
        Errors errors = createValidationErrors(entity, "course feedback");

        validator.validate(entity, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(userRepository, times(1)).findOne(student.getId());
    }

    @Test
    public void singleRoleSingleFieldFailedValidationTest() throws Exception {
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                User.Role.STUDENT);
        UserRoleValidator<CourseFeedback, Integer> validator = new UserRoleValidator<>(studentFieldInfo, userRepository, courseFeedbackRepository);

        CourseFeedback entity = courseFeedbackSupplier.getValidEntityWithId();
        User teacher = userSupplier.getWithRole(User.Role.TEACHER);
        teacher.setId(entity.getStudent().getId());

        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(teacher);
        Errors errors = createValidationErrors(entity, "course feedback");

        validator.validate(entity, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("student", fieldError.getField());
        verify(userRepository, times(1)).findOne(teacher.getId());
    }

    @Test
    public void multipleRolesSingleFieldSuccessValidationTest() throws Exception {
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                Arrays.asList(User.Role.ADMIN, User.Role.TEACHER));
        UserRoleValidator<CourseFeedback, Integer> validator = new UserRoleValidator<>(studentFieldInfo, userRepository, courseFeedbackRepository);
        CourseFeedback entity = courseFeedbackSupplier.getValidEntityWithId();
        Errors errors = createValidationErrors(entity, "course feedback");

        User admin = userSupplier.getWithRole(User.Role.ADMIN);
        admin.setId(entity.getStudent().getId());

        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(admin);

        validator.validate(entity, errors);

        User teacher = userSupplier.getWithRole(User.Role.TEACHER);
        teacher.setId(entity.getStudent().getId());

        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(teacher);

        validator.validate(entity, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(userRepository, times(2)).findOne(entity.getStudent().getId());
    }

    @Test
    public void multipleRolesSingleFieldFailedValidationTest() throws Exception {
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                Arrays.asList(User.Role.ADMIN, User.Role.TEACHER));
        UserRoleValidator<CourseFeedback, Integer> validator = new UserRoleValidator<>(studentFieldInfo, userRepository, courseFeedbackRepository);
        CourseFeedback entity = courseFeedbackSupplier.getValidEntityWithId();
        Errors errors = createValidationErrors(entity, "course feedback");

        User student = userSupplier.getWithRole(User.Role.STUDENT);
        student.setId(entity.getStudent().getId());
        when(userRepository.findOne(entity.getStudent().getId())).thenReturn(student);

        validator.validate(entity, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("student", fieldError.getField());
        verify(userRepository, times(1)).findOne(student.getId());
    }

    private UserRoleValidator<CourseFeedback, Integer> getEmptyValidator() {
        return new UserRoleValidator<>(Collections.emptyList(), userRepository, courseFeedbackRepository);
    }
}
