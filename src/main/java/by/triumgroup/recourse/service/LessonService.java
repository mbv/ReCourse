package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LessonService extends CrudService<Lesson, Integer> {

    Optional<Lesson> update(Lesson entity, Integer id, User.Role performerRole);

    Optional<List<Lesson>> findByCourseId(Integer id, Pageable pageable);

    Optional<List<Lesson>> findByTeacherId(Integer id, Pageable pageable);

    Optional<List<Lesson>> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable);

    Optional<List<Lesson>> findFutureLessonsByUserId(Integer userId, Pageable pageable);

    Optional<List<Lesson>> findPastLessonsByUserId(Integer userId, Pageable pageable);
}
