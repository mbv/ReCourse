package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.Lesson;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.service.LessonService;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class LessonServiceImpl
        extends AbstractCrudService<Lesson, Integer>
        implements LessonService {

    private final LessonRepository repository;

    public LessonServiceImpl(LessonRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Lesson> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByCourseIdOrderByStartTimeDesc(id, pageable));
    }

    @Override
    public List<Lesson> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTeacherIdOrderByStartTimeDesc(id, pageable));
    }

    @Override
    public List<Lesson> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable) {
        return wrapJPACall(() ->
                repository.findByTeacherIdAndCourseIdOrderByStartTimeDesc(teacherId, courseId, pageable));
    }
}
