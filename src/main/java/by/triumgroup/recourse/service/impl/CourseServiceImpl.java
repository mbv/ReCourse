package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.exception.ServiceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Course> searchByTitle(String title, Pageable pageable) {
        return wrapJPACall(() -> courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(title, pageable));
    }

    @Override
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> courseRepository.findByStatusOrderByIdDesc(status, pageable));
    }

    @Override
    public void registerStudentToCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));
        validateUserAndCourseToRegisterForCourse(user, course, force);
        Set<User> registeredStudents = course.getStudents();
        registeredStudents.add(user);
        wrapJPACall(() -> courseRepository.save(course));
    }

    @Override
    public void removeStudentFromCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));
        validateUserAndCourseToRemoveFromCourse(user, course, force);
        Set<User> registeredStudents = course.getStudents();
        registeredStudents.remove(user);
        wrapJPACall(() -> courseRepository.save(course));
    }

    private void checkUserAndCourseExistence(User user, Course course) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        if (course == null) {
            errorMessages.add(new ErrorMessage("course", "Not found"));
        }
        if (user == null) {
            errorMessages.add(new ErrorMessage("user", "Not found"));
        }
        if (!errorMessages.isEmpty()) {
            throw new ServiceNotFoundException(errorMessages);
        }
    }

    private void validateUserAndCourseToRegisterForCourse(User user, Course course, boolean force) {
        checkUserAndCourseExistence(user, course);
        List<ErrorMessage> errorMessages = new ArrayList<>();
        if (user.getRole() != User.Role.STUDENT) {
            errorMessages.add(new ErrorMessage("user", "User must be a student"));
        }
        if (course.getStatus() != Course.Status.REGISTRATION && !force) {
            errorMessages.add(new ErrorMessage("course", "Course must has registration status"));
        }
        if (!errorMessages.isEmpty()) {
            throw new ServiceBadRequestException(errorMessages);
        }
    }

    private void validateUserAndCourseToRemoveFromCourse(User user, Course course, boolean force) {
        checkUserAndCourseExistence(user, course);
        if (course.getStatus() == Course.Status.FINISHED && !force) {
            throw new ServiceBadRequestException(
                    new ErrorMessage("course", "Cannot unregister from finished course")
            );
        }
    }

    @Override
    protected String getEntityName() {
        return "course";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

}
