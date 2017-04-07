package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentReportService extends CrudService<StudentReport, Integer> {

    List<StudentReport> findByTeacherId(Integer id, Pageable pageable);

    List<StudentReport> findByStudentId(Integer id, Pageable pageable);

    List<StudentReport> findByCourseId(Integer id, Pageable pageable);

}
