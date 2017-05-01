package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Integer> {

    List<HometaskSolution> findByLessonId(Integer lessonId, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer studentId, Pageable pageable);

    HometaskSolution findByStudentIdAndLessonId(Integer studentId, Integer lessonId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hometask_solution WHERE (hometask_solution.student_id = ?1) AND (hometask_solution.lesson_id IN (SELECT lesson.id FROM lesson WHERE (lesson.course_id = ?2)))",
            nativeQuery = true)
    void deleteByStudentIdCourseId(Integer studentId, Integer courseId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hometask_solution WHERE (hometask_solution.lesson_id IN (SELECT lesson.id FROM lesson WHERE (lesson.course_id = ?1)))",
            nativeQuery = true)
    void deleteByCourseId(Integer courseId);


}
