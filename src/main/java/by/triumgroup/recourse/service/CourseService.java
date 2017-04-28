package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService extends CrudService<Course, Integer> {

    Optional<List<Course>> searchByTitle(String title, Pageable pageable);

    Optional<List<Course>> findByStatus(Course.Status status, Pageable pageable);

    void registerStudentToCourse(Integer courseId, Integer studentId, boolean force);

    void removeStudentFromCourse(Integer courseId, Integer studentId, boolean force);

    Optional<List<Course>> findAvailableForUser(Integer userId, Pageable pageable);

    Optional<List<Course>> findRegisteredForUser(Integer userId, Pageable pageable);

    List<User> findStudentsForCourse(Integer courseId);
}
