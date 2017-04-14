package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.StudentReportService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class CourseControllerImpl
        extends AbstractCrudController<Course, Integer>
        implements CourseController {

    private static final Logger logger = getLogger(CourseControllerImpl.class);
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;
    private final StudentReportService studentReportService;

    public CourseControllerImpl(CourseService courseService,
                                LessonService lessonService,
                                CourseFeedbackService courseFeedbackService,
                                StudentReportService studentReportService
    ) {
        super(courseService, logger);
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseFeedbackService = courseFeedbackService;
        this.studentReportService = studentReportService;
    }

    @Override
    public List<Lesson> getLessons(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<Lesson>> lessons = lessonService.findByCourseId(courseId, pageable);
            return lessons.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<CourseFeedback> getFeedbacks(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(courseId, pageable);
            return feedbacks.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<StudentReport> getReports(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<StudentReport>> reports = studentReportService.findByCourseId(courseId, pageable);
            return reports.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.searchByTitle(title, pageable));
    }

    @Override
    public List<Course> searchByStatus(@RequestParam("status") Course.Status status, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable));
    }
}
