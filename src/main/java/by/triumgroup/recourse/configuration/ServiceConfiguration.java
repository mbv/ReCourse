package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }


}
