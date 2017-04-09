package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByTeacherIdOrderByIdDesc(Integer id, Pageable pageable);

    List<Course> findByTeacherIdAndStatusOrderByIdDesc(Integer id, Course.Status status, Pageable pageable);

    List<Course> findByOrganizerIdOrderByIdDesc(Integer id, Pageable pageable);

    List<Course> findByOrganizerIdAndStatusOrderByIdDesc(Integer id, Course.Status status, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);

    List<Course> findAllByOrderByIdDesc(Pageable pageable);
}
