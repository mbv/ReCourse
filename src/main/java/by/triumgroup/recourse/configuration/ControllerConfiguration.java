package by.triumgroup.recourse.configuration;


import by.triumgroup.recourse.controller.*;
import by.triumgroup.recourse.controller.impl.*;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
public class ControllerConfiguration {

    @Bean
    public UserController userController(UserService userService, RegistrationDetailsValidator registrationDetailsValidator, AuthorizationServerTokenServices authorizationServerTokenServices, DefaultTokenServices defaultTokenServices) {
        return new UserControllerImpl(userService, defaultTokenServices);
    }

    @Bean
    public CourseController courseController(
            CourseService courseService,
            LessonService lessonService,
            CourseFeedbackService courseFeedbackService) {
        return new CourseControllerImpl(courseService, lessonService, courseFeedbackService);
    }

    @Bean
    public CourseFeedbackController courseFeedbackController(CourseFeedbackService courseFeedbackService) {
        return new CourseFeedbackControllerImpl(courseFeedbackService);
    }

    @Bean
    public HometaskSolutionController hometaskSolutionController(
            HometaskSolutionService hometaskSolutionSevice,
            MarkService markService,
            LessonService lessonService) {
        return new HometaskSolutionControllerImpl(hometaskSolutionSevice, markService, lessonService);
    }

    @Bean
    public LessonController lessonController(LessonService lessonService) {
        return new LessonControllerImpl(lessonService);
    }

    @Bean
    public MarkController markController(
            MarkService markService,
            HometaskSolutionService hometaskSolutionService,
            LessonService lessonService
    ) {
        return new MarkControllerImpl(markService, hometaskSolutionService, lessonService);
    }

    @Bean
    public StudentController studentController(HometaskSolutionService hometaskSolutionService) {
        return new StudentControllerImpl(hometaskSolutionService);
    }

    @Bean
    public TeacherController teacherController(
            LessonService lessonService) {
        return new TeacherControllerImpl(lessonService);
    }

}

