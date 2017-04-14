package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.repository.*;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public CourseService courseService(
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new CourseServiceImpl(courseRepository, userRepository);
    }

    @Bean
    public CourseFeedbackService courseFeedbackService(
            CourseFeedbackRepository courseFeedbackRepository,
            CourseRepository courseRepository) {
        return new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository);
    }

    @Bean
    public HometaskService hometaskService(
            HometaskRepository hometaskRepository) {
        return new HometaskServiceImpl(hometaskRepository);
    }

    @Bean
    public HometaskSolutionService hometaskSolutionService(
            HometaskSolutionRepository hometaskSolutionRepository,
            HometaskRepository hometaskRepository,
            UserRepository userRepository) {
        return new HometaskSolutionServiceImpl(hometaskSolutionRepository, hometaskRepository, userRepository);
    }

    @Bean
    public LessonService lessonService(
            LessonRepository lessonRepository,
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new LessonServiceImpl(lessonRepository, courseRepository, userRepository);
    }

    @Bean
    public MarkService markService(MarkRepository markRepository) {
        return new MarkServiceImpl(markRepository);
    }

    @Bean
    public StudentReportService studentReportService(
            StudentReportRepository studentReportRepository,
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new StudentReportServiceImpl(studentReportRepository, courseRepository, userRepository);
    }

    @Bean
    public TeacherFeedbackService teacherFeedbackService(
            TeacherFeedbackRepository teacherFeedbackRepository,
            UserRepository userRepository) {
        return new TeacherFeedbackServiceImpl(teacherFeedbackRepository, userRepository);
    }

}
