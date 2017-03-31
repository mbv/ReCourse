package by.triumgroup.recourse.configuration;


import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.impl.UserControllerImpl;
import by.triumgroup.recourse.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public UserController userController(UserService userService) {
        return new UserControllerImpl(userService);
    }

}

