package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCaseOrderByIdDesc(String title, Pageable pageable);

    List<Course> findByStatusOrderByIdDesc(Course.Status status, Pageable pageable);

    @Modifying
    @Procedure("recourse.update_teacher")
    void updateTeacher(@Param("course_id") Integer courseId, @Param("new_teacher_id") Integer teacherId);

    @Query(value = "SELECT recourse.can_update_teacher(:course_id, :new_teacher_id", nativeQuery = true)
    boolean canUpdateTeacher(@Param("course_id") Integer courseId, @Param("new_teacher_id") Integer teacher_id);
}
