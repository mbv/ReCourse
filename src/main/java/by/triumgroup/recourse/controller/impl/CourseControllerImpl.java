package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.entity.Course;
import by.triumgroup.recourse.entity.CourseFeedback;
import by.triumgroup.recourse.entity.Lesson;
import by.triumgroup.recourse.entity.StudentReport;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.StudentReportService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return wrapServiceCall(logger, () -> lessonService.findByCourseId(courseId, pageable));
    }

    @Override
    public List<CourseFeedback> getFeedbacks(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseFeedbackService.findByCourseId(courseId, pageable));
    }

    @Override
    public List<StudentReport> getReports(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> studentReportService.findByCourseId(courseId, pageable));
    }

    @Override
    public List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.searchByTitle(title, pageable));
    }

    @Override
    public List<Course> getAll(@RequestParam(value = "status", required = false) Course.Status status, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable));
    }
}
