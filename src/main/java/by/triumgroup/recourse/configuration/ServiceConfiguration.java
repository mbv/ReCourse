package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.impl.UserServiceImpl;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, RegistrationDetailsValidator registrationDetailsValidator) {
        return new UserServiceImpl(userRepository, registrationDetailsValidator);
    }


}
