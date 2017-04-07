package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.Lesson;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService extends CrudService<Lesson, Integer> {

    List<Lesson> findByCourseId(Integer id, Pageable pageable);

    List<Lesson> findByTeacherId(Integer id, Pageable pageable);

    List<Lesson> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable);

}
