package by.triumgroup.recourse.configuration;


import by.triumgroup.recourse.controller.*;
import by.triumgroup.recourse.controller.impl.*;
import by.triumgroup.recourse.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public UserController userController(UserService userService) {
        return new UserControllerImpl(userService);
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
            HometaskSolutionService hometaskSolutionService) {
        return new HometaskControllerImpl(hometaskService, hometaskSolutionService);
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
    public MarkController markController(MarkService markService) {
        return new MarkControllerImpl(markService);
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

