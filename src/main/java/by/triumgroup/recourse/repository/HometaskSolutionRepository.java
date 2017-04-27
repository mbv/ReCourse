package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Integer> {

    List<HometaskSolution> findByLessonId(Integer lessonId, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer studentId, Pageable pageable);

    HometaskSolution findByStudentIdAndLessonId(Integer studentId, Integer lessonId);

    Long deleteByLessonId(Integer lessonId);

    @Query(value = "DELETE FROM hometask_solution AS h WHERE (h.student_id = ?1) " +
            "AND (h.lesson_id IN (SELECT l.id FROM lesson AS l WHERE (l.course_id = ?2)))",
            nativeQuery = true)
    Long deleteByStudentIdCourseId(Integer studentId, Integer courseId);

    @Query(value = "DELETE FROM hometask_solution AS h WHERE " +
            "(h.lesson_id IN (SELECT l.id FROM lesson AS l WHERE (l.course_id = ?2)))",
            nativeQuery = true)
    Long deleteByCourseId(Integer courseId);


}
