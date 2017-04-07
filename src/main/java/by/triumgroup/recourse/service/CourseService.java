package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService extends CrudService<Course, Integer> {

    List<Course> searchByTitle(String title, Pageable pageable);

    List<Course> findByTeacherId(Integer id, Pageable pageable);

    List<Course> findByTeacherIdAndStatus(Integer id, Course.Status status, Pageable pageable);

    List<Course> findByOrganizerId(Integer id, Pageable pageable);

    List<Course> findByOrganizerIdAndStatus(Integer id, Course.Status status, Pageable pageable);

    List<Course> findAll(Pageable pageable);

    List<Course> findByStatus(Course.Status status, Pageable pageable);

}
