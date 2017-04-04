package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Integer> {

    List<Lesson> findByCourseIdOrderByStartTimeDesc(Integer id);

    List<Lesson> findByCourseIdOrderByStartTimeDesc(Integer id, Pageable pageable);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(Integer id);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(Integer id, Pageable pageable);

}
