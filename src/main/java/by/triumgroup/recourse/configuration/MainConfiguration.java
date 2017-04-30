package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.configuration.security.SecurityConfiguration;
import by.triumgroup.recourse.controller.exception.RestExceptionHandler;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import by.triumgroup.recourse.validation.validator.PasswordChangingValidator;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import static by.triumgroup.recourse.util.Util.allItemsPage;

@Configuration
@Import({
        PersistenceConfiguration.class,
        ServiceConfiguration.class,
        ControllerConfiguration.class,
        SecurityConfiguration.class,
        PageRequestConfiguration.class
})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration.class
})
public class MainConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainConfiguration.class);
    }

    @Bean
    DefaultErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }

    @Bean
    RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

    @Bean
    RegistrationDetailsValidator registrationDetailsValidator(UserRepository userRepository) {
        return new RegistrationDetailsValidator(userRepository);
    }

    @Bean
    PasswordChangingValidator passwordChangingValidator() {
        return new PasswordChangingValidator();
    }

    @Bean
    LessonTimeValidator lessonTimeValidator(LessonRepository lessonRepository) {
        return new LessonTimeValidator(lessonRepository);
    }

    @Bean
    PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        resolver.setFallbackPageable(allItemsPage());
        return resolver;
    }
}