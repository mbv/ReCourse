package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.TeacherController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
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

public class TeacherControllerImpl implements TeacherController {

    private static final Logger logger = getLogger(TeacherControllerImpl.class);
    private final CourseService courseService;
    private final LessonService lessonService;

    public TeacherControllerImpl(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @Override
    public List<Course> getCoursesByStatus(
            @RequestParam(value = "status") Course.Status status,
            Pageable pageable
    ) {
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable));
    }

    @Override
    public List<Lesson> getLessons(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            Pageable pageable
    ) {
        return wrapServiceCall(logger, () -> {
            Optional<List<Lesson>> lessons;
            if (courseId == null) {
                lessons = lessonService.findByTeacherId(teacherId, pageable);
            } else {
                lessons = lessonService.findByTeacherIdAndCourseId(teacherId, courseId, pageable);
            }
            return lessons.orElseThrow(NotFoundException::new);
        });
    }

}
