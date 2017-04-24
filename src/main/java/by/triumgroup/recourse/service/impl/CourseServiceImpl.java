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

import java.util.*;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
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
    public void registerStudentToCourse(Integer studentId, Integer courseId) {
        Optional<Course> courseOptional = wrapJPACallToOptional(() -> courseRepository.findOne(courseId));
        Optional<User> user = wrapJPACallToOptional(() -> userRepository.findOne(studentId));
        List<ErrorMessage> errorMessages = new ArrayList<>();
        if (!courseOptional.isPresent()) {
            errorMessages.add(new ErrorMessage("course", "Not found"));
        }
        if (!user.isPresent()) {
            errorMessages.add(new ErrorMessage("user", "Not found"));
        }
        if (!errorMessages.isEmpty()) {
            throw new ServiceNotFoundException(errorMessages);
        }
        if (user.get().getRole() != User.Role.STUDENT) {
            errorMessages.add(new ErrorMessage("user", "User must be a student"));
        }
        Course course = courseOptional.get();
        if (course.getStatus() != Course.Status.REGISTRATION) {
            errorMessages.add(new ErrorMessage("course", "Course must has registration status"));
        }
        if (!errorMessages.isEmpty()) {
            throw new ServiceBadRequestException(errorMessages);
        }
        Set<User> registeredStudents = course.getStudents();
        registeredStudents.add(user.get());
        wrapJPACall(() -> courseRepository.save(course));
    }

    @Override
    public void removeStudentFromCourse(Integer studentId, Integer courseId) {
        Optional<Course> courseOptional = wrapJPACallToOptional(() -> courseRepository.findOne(courseId));
        Optional<User> user = wrapJPACallToOptional(() -> userRepository.findOne(studentId));
        List<ErrorMessage> errorMessages = new ArrayList<>();
        if (!courseOptional.isPresent()) {
            errorMessages.add(new ErrorMessage("course", "Not found"));
        }
        if (!user.isPresent()) {
            errorMessages.add(new ErrorMessage("user", "Not found"));
        }
        if (!errorMessages.isEmpty()) {
            throw new ServiceNotFoundException(errorMessages);
        }
        Course course = courseOptional.get();
        if (course.getStatus() == Course.Status.FINISHED) {
            errorMessages.add(new ErrorMessage("course", "Cannot unregister from finished course"));
        }
        Set<User> registeredStudents = course.getStudents();
        registeredStudents.remove(user.get());
        wrapJPACall(() -> courseRepository.save(course));
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
