package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LessonService extends CrudService<Lesson, Integer> {

    Optional<List<Lesson>> findByCourseId(Integer id, Pageable pageable);

    Optional<List<Lesson>> findByTeacherId(Integer id, Pageable pageable);

    Optional<List<Lesson>> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable);

}
