package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.repository.*;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public CourseService courseService(CourseRepository courseRepository) {
        return new CourseServiceImpl(courseRepository);
    }

    @Bean
    public CourseFeedbackService courseFeedbackService(CourseFeedbackRepository courseFeedbackRepository) {
        return new CourseFeedbackServiceImpl(courseFeedbackRepository);
    }

    @Bean
    public HometaskService hometaskService(HometaskRepository hometaskRepository) {
        return new HometaskServiceImpl(hometaskRepository);
    }

    @Bean
    public HometaskSolutionService hometaskSolutionService(HometaskSolutionRepository hometaskSolutionRepository) {
        return new HometaskSolutionServiceImpl(hometaskSolutionRepository);
    }

    @Bean
    public LessonService lessonService(LessonRepository lessonRepository) {
        return new LessonServiceImpl(lessonRepository);
    }

    @Bean
    public MarkService markService(MarkRepository markRepository) {
        return new MarkServiceImpl(markRepository);
    }

    @Bean
    public StudentReportService studentReportService(StudentReportRepository studentReportRepository) {
        return new StudentReportServiceImpl(studentReportRepository);
    }

    @Bean
    public TeacherFeedbackService teacherFeedbackService(TeacherFeedbackRepository teacherFeedbackRepository) {
        return new TeacherFeedbackServiceImpl(teacherFeedbackRepository);
    }

}
