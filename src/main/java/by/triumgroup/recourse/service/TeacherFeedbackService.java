package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.TeacherFeedback;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherFeedbackService extends CrudService<TeacherFeedback, Integer> {

    List<TeacherFeedback> findByTeacherId(Integer id, Pageable pageable);

    List<TeacherFeedback> findByStudentId(Integer id, Pageable pageable);

}
