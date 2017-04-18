package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByTeacherIdOrderByIdDesc(Integer id, Pageable pageable);

    List<Course> findByTeacherIdAndStatusOrderByIdDesc(Integer id, Course.Status status, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);

    @Modifying
    @Query( value = "UPDATE recourse.lesson set teacher_id = :teacherId WHERE (start_time > current_timestamp) AND (course_id = :courseId)",
            nativeQuery = true)
    void updateTeacher(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);
}
