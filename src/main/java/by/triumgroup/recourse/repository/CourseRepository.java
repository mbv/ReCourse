package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);
}
