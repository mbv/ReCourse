package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.StudentController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class StudentControllerImpl implements StudentController {

    private static final Logger logger = getLogger(StudentControllerImpl.class);
    private final LessonService lessonService;

    public StudentControllerImpl(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    public List<Lesson> getFutureForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> lessonService.findFutureLessonsByUserId(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Lesson> getPastForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> lessonService.findPastLessonsByUserId(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }
}
