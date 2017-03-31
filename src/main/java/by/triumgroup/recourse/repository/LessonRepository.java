package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {

    List<Lesson> findByCourseIdOrderByStartTimeDesc(Long id);

    List<Lesson> findByCourseIdOrderByStartTimeDesc(Long id, Pageable pageable);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(Long id);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(Long id, Pageable pageable);

}
