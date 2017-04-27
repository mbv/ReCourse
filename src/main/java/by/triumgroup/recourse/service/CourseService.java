package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService extends CrudService<Course, Integer> {

    List<Course> searchByTitle(String title, Pageable pageable);

    List<Course> findByStatus(Course.Status status, Pageable pageable);

    List<Course> findAvailableForUser(Integer userId, Pageable pageable);

    List<Course> findRegisteredForUser(Integer userId, Pageable pageable);

    void registerStudentToCourse(Integer courseId, Integer studentId, boolean force);

    void removeStudentFromCourse(Integer courseId, Integer studentId, boolean force);

    List<User> findStudentsForCourse(Integer courseId);
}
