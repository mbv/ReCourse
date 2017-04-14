package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService extends CrudService<Course, Integer> {

    List<Course> searchByTitle(String title, Pageable pageable);

    Optional<List<Course>> findByTeacherId(Integer id, Pageable pageable);

    Optional<List<Course>> findByTeacherIdAndStatus(Integer id, Course.Status status, Pageable pageable);

    Optional<List<Course>> findByOrganizerId(Integer id, Pageable pageable);

    Optional<List<Course>> findByOrganizerIdAndStatus(Integer id, Course.Status status, Pageable pageable);

    List<Course> findByStatus(Course.Status status, Pageable pageable);

}
