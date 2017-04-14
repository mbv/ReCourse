package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeacherFeedbackService extends CrudService<TeacherFeedback, Integer> {

    Optional<List<TeacherFeedback>> findByTeacherId(Integer id, Pageable pageable);

    Optional<List<TeacherFeedback>> findByStudentId(Integer id, Pageable pageable);

}
