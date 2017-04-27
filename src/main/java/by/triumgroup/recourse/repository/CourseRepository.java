package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);

    @Query(value = "SELECT * FROM course LEFT JOIN course_student ON (course.id = course_student.course_id) AND (course_student.student_id IS NULL) WHERE (course.status = 'ONGOING') AND (course_student.student_id = ?1) ORDER BY id DESC ?#{#pageable}",
            nativeQuery = true)
    Page<Course> findOngoingForUser(Integer userId, Pageable pageable);
}
