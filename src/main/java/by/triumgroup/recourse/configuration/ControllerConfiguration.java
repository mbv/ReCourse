package by.triumgroup.recourse.configuration;


import by.triumgroup.recourse.controller.*;
import by.triumgroup.recourse.controller.impl.*;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.validation.RegistrationDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
public class ControllerConfiguration {

    @Bean
    public UserController userController(UserService userService, RegistrationDetailsValidator registrationDetailsValidator, AuthorizationServerTokenServices authorizationServerTokenServices, DefaultTokenServices defaultTokenServices) {
        return new UserControllerImpl(userService, registrationDetailsValidator, authorizationServerTokenServices, defaultTokenServices);
    }

    @Bean
    public CourseController courseController(
            CourseService courseService,
            LessonService lessonService,
            CourseFeedbackService courseFeedbackService,
            StudentReportService studentReportService) {
        return new CourseControllerImpl(courseService, lessonService, courseFeedbackService, studentReportService);
    }

    @Bean
    public CourseFeedbackController courseFeedbackController(CourseFeedbackService courseFeedbackService) {
        return new CourseFeedbackControllerImpl(courseFeedbackService);
    }

    @Bean
    public HometaskController hometaskController(
            HometaskService hometaskService,
            HometaskSolutionService hometaskSolutionService,
            LessonService lessonService) {
        return new HometaskControllerImpl(hometaskService, hometaskSolutionService, lessonService);
    }

    @Bean
    public HometaskSolutionController hometaskSolutionController(
            HometaskSolutionService hometaskSolutionSevice,
            MarkService markService) {
        return new HometaskSolutionControllerImpl(hometaskSolutionSevice, markService);
    }

    @Bean
    public LessonController lessonController(
            LessonService lessonService,
            HometaskService hometaskService) {
        return new LessonControllerImpl(lessonService, hometaskService);
    }

    @Bean
    public MarkController markController(
            MarkService markService,
            HometaskSolutionService hometaskSolutionService,
            HometaskService hometaskService,
            LessonService lessonService
    ) {
        return new MarkControllerImpl(markService, hometaskSolutionService, hometaskService, lessonService);
    }

    @Bean
    public OrganizerController organizerController(CourseService courseService) {
        return new OrganizerControllerImpl(courseService);
    }

    @Bean
    public StudentController studentController(
            StudentReportService studentReportService,
            TeacherFeedbackService teacherFeedbackService,
            HometaskSolutionService hometaskSolutionService) {
        return new StudentControllerImpl(studentReportService, teacherFeedbackService, hometaskSolutionService);
    }

    @Bean
    public StudentReportController studentReportController(StudentReportService studentReportService) {
        return new StudentReportControllerImpl(studentReportService);
    }

    @Bean
    public TeacherController teacherController(
            CourseService courseService,
            LessonService lessonService,
            StudentReportService studentReportService,
            TeacherFeedbackService teacherFeedbackService) {
        return new TeacherControllerImpl(courseService, lessonService, studentReportService, teacherFeedbackService);
    }

    @Bean
    public TeacherFeedbackController teacherFeedbackController(TeacherFeedbackService teacherFeedbackService) {
        return new TeacherFeedbackControllerImpl(teacherFeedbackService);
    }

}

