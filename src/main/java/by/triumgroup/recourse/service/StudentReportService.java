package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.StudentReport;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentReportService extends CrudService<StudentReport, Integer> {

    Optional<List<StudentReport>> findByTeacherId(Integer id, Pageable pageable);

    Optional<List<StudentReport>> findByStudentId(Integer id, Pageable pageable);

    Optional<List<StudentReport>> findByCourseId(Integer id, Pageable pageable);

}
