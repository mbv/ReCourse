package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.repository.*;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.service.impl.*;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RegistrationDetailsValidator registrationDetailsValidator) {
        return new UserServiceImpl(userRepository, passwordEncoder, registrationDetailsValidator);
    }

    @Bean
    public CourseService courseService(
            CourseRepository courseRepository) {
        return new CourseServiceImpl(courseRepository);
    }

    @Bean
    public CourseFeedbackService courseFeedbackService(
            CourseFeedbackRepository courseFeedbackRepository,
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository, userRepository);
    }

    @Bean
    public HometaskSolutionService hometaskSolutionService(
            HometaskSolutionRepository hometaskSolutionRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository) {
        return new HometaskSolutionServiceImpl(hometaskSolutionRepository, userRepository, lessonRepository);
    }

    @Bean
    public LessonService lessonService(
            LessonRepository lessonRepository,
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonTimeValidator lessonTimeValidator) {
        return new LessonServiceImpl(lessonRepository, courseRepository, userRepository, lessonTimeValidator);
    }

    @Bean
    public MarkService markService(MarkRepository markRepository) {
        return new MarkServiceImpl(markRepository);
    }


}
