package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.supplier.entity.dto.RegistrationDetailsSupplier;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static by.triumgroup.recourse.util.TestUtil.createValidationErrors;
import static org.mockito.Mockito.when;

public class RegistrationDetailsValidatorTest {
    @InjectMocks
    private RegistrationDetailsValidator validator;

    @Mock
    private UserRepository userRepository;

    private RegistrationDetailsSupplier registrationDetailsSupplier;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        registrationDetailsSupplier = new RegistrationDetailsSupplier();
    }


    @Test
    public void validRegistrationTest() {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userRepository.findByEmail(registrationDetails.getEmail())).thenReturn(null);
        Errors errors = createValidationErrors(registrationDetails, "registrationDetails");

        validator.validate(registrationDetails, errors);

        Assert.assertFalse(errors.hasFieldErrors());
    }

    @Test
    public void registrationWithExistingEmailTest() {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        when(userRepository.findByEmail(registrationDetails.getEmail())).thenReturn(new User());
        Errors errors = createValidationErrors(registrationDetails, "registrationDetails");

        validator.validate(registrationDetails, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("email", fieldError.getField());
    }

    @Test
    public void registrationWithDifferentPasswordsTest() {
        RegistrationDetails registrationDetails = registrationDetailsSupplier.get();
        Errors errors = createValidationErrors(registrationDetails, "registrationDetails");
        String password = registrationDetails.getPassword();
        String passwordConfirmation = String.format("%s %s", password, password);
        registrationDetails.setPasswordConfirmation(passwordConfirmation);

        validator.validate(registrationDetails, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("password", fieldError.getField());
    }
}
