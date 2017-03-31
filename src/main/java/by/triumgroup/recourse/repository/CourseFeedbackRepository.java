package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.CourseFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseFeedbackRepository extends PagingAndSortingRepository<CourseFeedback, Long> {

    List<CourseFeedback> findByCourseIdOrderByIdDesc(Long courseId);

    List<CourseFeedback> findByCourseIdOrderByIdDesc(Long courseId, Pageable pageable);

}