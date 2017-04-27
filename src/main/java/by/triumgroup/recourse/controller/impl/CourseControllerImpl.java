package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
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

    public CourseControllerImpl(CourseService courseService,
                                LessonService lessonService,
                                CourseFeedbackService courseFeedbackService
    ) {
        super(courseService, logger);
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseFeedbackService = courseFeedbackService;
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
    public List<User> getStudents(@PathVariable("courseId") Integer courseId) {
        return wrapServiceCall(logger, () -> courseService.findStudentsForCourse(courseId));
    }

    @Override
    public List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.searchByTitle(title, pageable));
    }

    @Override
    public List<Course> searchByStatus(@RequestParam("status") Course.Status status, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable));
    }

    @Override
    public List<Course> getAvailableForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findAvailableForUser(studentId, pageable));
    }

    @Override
    public List<Course> getRegisteredForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findRegisteredForUser(studentId, pageable));
    }

    @Override
    protected boolean hasAuthorityToEdit(Course entity, UserAuthDetails authDetails) {
        return authDetails.getRole() == User.Role.ADMIN;
    }

    public void registerToCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void unregisterFromCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void registerStudentToCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, studentId, true));
    }

    @Override
    public void unregisterStudentFromCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, studentId, true));
    }
}
