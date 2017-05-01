package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);

    @Query(value = "SELECT *\n" +
            "FROM course\n" +
            "  LEFT JOIN course_student ON ((course.id = course_student.course_id) AND\n" +
            "                              (course_student.student_id != ?1) OR (course_student.course_id IS NULL))\n" +
            "WHERE (course.status = 'PUBLISHED') AND (course.registration_end > NOW())\n" +
            "GROUP BY id\n" +
            "ORDER BY id DESC\n" +
            "#pageable\n",
            nativeQuery = true)
    List<Course> findAvailableForUser(Integer userId, Pageable pageable);

    @Query(value = "SELECT *\n" +
            "FROM course\n" +
            "  JOIN course_student ON ((course.id = course_student.course_id) AND\n" +
            "                              (course_student.student_id = ?1))\n" +
            "GROUP BY id\n" +
            "ORDER BY id DESC\n" +
            "#pageable\n",
            nativeQuery = true)
    List<Course> findRegisteredForUser(Integer userId, Pageable pageable);
}
