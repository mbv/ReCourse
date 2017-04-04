package by.triumgroup.recourse.repository;


import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReportRepository extends PagingAndSortingRepository<StudentReport, Integer> {

    List<StudentReport> findByTeacherId(Integer id);

    List<StudentReport> findByTeacherId(Integer id, Pageable pageable);

    List<StudentReport> findByStudentId(Integer id);

    List<StudentReport> findByStudentId(Integer id, Pageable pageable);

    List<StudentReport> findByCourseId(Integer id);

    List<StudentReport> findByCourseId(Integer id, Pageable pageable);

}
