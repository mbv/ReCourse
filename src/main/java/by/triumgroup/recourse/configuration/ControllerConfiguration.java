package by.triumgroup.recourse.configuration;


import by.triumgroup.recourse.controller.*;
import by.triumgroup.recourse.controller.impl.*;
import by.triumgroup.recourse.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
public class ControllerConfiguration {

    @Bean
    public UserController userController(UserService userService, DefaultTokenServices defaultTokenServices) {
        return new UserControllerImpl(userService, defaultTokenServices);
    }

    @Bean
    public CourseController courseController(
            CourseService courseService,
            LessonService lessonService,
            CourseFeedbackService courseFeedbackService,
            UserService userService) {
        return new CourseControllerImpl(courseService, lessonService, courseFeedbackService, userService);
    }

    @Bean
    public CourseFeedbackController courseFeedbackController(CourseFeedbackService courseFeedbackService,
                                                             UserService userService) {
        return new CourseFeedbackControllerImpl(courseFeedbackService, userService);
    }

    @Bean
    public HometaskSolutionController hometaskSolutionController(
            HometaskSolutionService hometaskSolutionSevice,
            MarkService markService,
            LessonService lessonService,
            UserService userService) {
        return new HometaskSolutionControllerImpl(hometaskSolutionSevice, markService, lessonService, userService);
    }

    @Bean
    public LessonController lessonController(LessonService lessonService,
                                             UserService userService) {
        return new LessonControllerImpl(lessonService, userService);
    }

    @Bean
    public MarkController markController(
            MarkService markService,
            HometaskSolutionService hometaskSolutionService,
            LessonService lessonService,
            UserService userService
    ) {
        return new MarkControllerImpl(markService, hometaskSolutionService, lessonService, userService);
    }

    @Bean
    public TeacherController teacherController(
            LessonService lessonService) {
        return new TeacherControllerImpl(lessonService);
    }

}

