package by.triumgroup.recourse.repository;


import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReportRepository extends PagingAndSortingRepository<StudentReport, Long> {

    List<StudentReport> findByTeacherId(Long id);

    List<StudentReport> findByTeacherId(Long id, Pageable pageable);

    List<StudentReport> findByStudentId(Long id);

    List<StudentReport> findByStudentId(Long id, Pageable pageable);

    List<StudentReport> findByCourseId(Long id);

    List<StudentReport> findByCourseId(Long id, Pageable pageable);

}
