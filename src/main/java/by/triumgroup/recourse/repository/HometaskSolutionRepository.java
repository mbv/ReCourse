package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Integer> {

    List<HometaskSolution> findByLessonId(Integer lessonId, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer studentId, Pageable pageable);

    HometaskSolution findByStudentIdAndLessonId(Integer studentId, Integer lessonId);

}
