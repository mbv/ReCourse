package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.dto.MarkedHometaskSolution;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Integer> {

    List<HometaskSolution> findByLessonId(Integer lessonId, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer studentId, Pageable pageable);

    HometaskSolution findByStudentIdAndLessonId(Integer studentId, Integer lessonId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hometask_solution WHERE (hometask_solution.student_id = :studentId) AND (hometask_solution.lesson_id IN (SELECT lesson.id FROM lesson WHERE (lesson.course_id = :courseId)))",
            nativeQuery = true)
    Integer deleteByStudentIdCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hometask_solution WHERE (hometask_solution.lesson_id IN (SELECT lesson.id FROM lesson WHERE (lesson.course_id = :courseId)))",
            nativeQuery = true)
    Integer deleteByCourseId(@Param("courseId") Integer courseId);

    List<MarkedHometaskSolution> findAllMarked(Pageable pageable);

    List<MarkedHometaskSolution> findMarkedByLessonId(@Param("lessonId") Integer lessonId, Pageable pageable);

    List<MarkedHometaskSolution> findMarkedByStudentId(@Param("studentId") Integer studentId, Pageable pageable);

}
