package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.TeacherFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherFeedbackRepository extends PagingAndSortingRepository<TeacherFeedback, Long> {

    List<TeacherFeedback> findByTeacherIdOrderByIdDesc(Long id);

    List<TeacherFeedback> findByTeacherIdOrderByIdDesc(Long id, Pageable pageable);

    List<TeacherFeedback> findByStudentIdOrderByIdDesc(Long id);

    List<TeacherFeedback> findByStudentIdOrderByIdDesc(Long id, Pageable pageable);

}
