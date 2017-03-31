package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByTeacherIdOrderByIdDesc(Long id, Pageable pageable);

    List<Course> findByTeacherIdAndStatusOrderByIdDesc(Long id, Course.Status status, Pageable pageable);

    List<Course> findByOrganizerIdOrderByIdDesc(Long id, Pageable pageable);

    List<Course> findByOrganizerIdAndStatusOrderByIdDesc(Long id, Course.Status status, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);
}
