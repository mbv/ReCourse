package by.triumgroup.recourse.validation.validator;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

@Component
public class RegistrationDetailsValidator implements Validator {

    private UserRepository userRepository;

    @Autowired
    public RegistrationDetailsValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationDetails registrationDetails = (RegistrationDetails) o;
        if (wrapJPACall(() -> userRepository.findByEmail(registrationDetails.getEmail())) != null) {
            errors.rejectValue("email", "user", "User is already exists");
        }
        if (!registrationDetails.getPassword().equals(registrationDetails.getPasswordConfirmation())){
            errors.rejectValue("password", "password", "Passwords do not match");
        }
    }

}
