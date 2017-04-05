package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        if (userRepository.findByEmail(registrationDetails.getEmail()) != null){
            errors.rejectValue("email", "User already exists");
        }
    }

}
