package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.exception.ServiceNotFoundException;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static org.slf4j.LoggerFactory.getLogger;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private static final Logger logger = getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final HometaskSolutionRepository hometaskSolutionRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             LessonRepository lessonRepository,
                             HometaskSolutionRepository hometaskSolutionRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.hometaskSolutionRepository = hometaskSolutionRepository;
    }

    @Override
    public Optional<List<Course>> searchByTitle(String title, Pageable pageable) {
        return wrapJPACallToOptional(() -> courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(title, pageable));
    }

    @Override
    public Optional<List<Course>> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACallToOptional(() -> courseRepository.findByStatusOrderByIdDesc(status, pageable));
    }

    @Override
    public Optional<List<Course>> findAvailableForUser(Integer userId, Pageable pageable) {
        return wrapJPACallToOptional(() -> courseRepository.findAvailableForUser(userId, pageable));
    }

    @Override
    public Optional<List<Course>> findRegisteredForUser(Integer userId, Pageable pageable) {
        return wrapJPACallToOptional(() -> courseRepository.findRegisteredForUser(userId, pageable));
    }

    @Override
    public Optional<Course> update(Course entity, Integer id) {
        Course course = wrapJPACall(() -> courseRepository.findOne(id));
        if (course != null && course.getStatus() != Course.Status.REGISTRATION && entity.getStatus() == Course.Status.REGISTRATION) {
            wrapJPACall(() -> hometaskSolutionRepository.deleteByCourseId(course.getId()));
        }
        return super.update(entity, id);
    }

    @Override
    public void registerStudentToCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));
        validateUserAndCourseToRegisterForCourse(user, course, force);
        Set<User> registeredStudents = course.getStudents();
        registeredStudents.add(user);
        wrapJPACall(() -> courseRepository.save(course));
        logger.debug("{} registered on curse {}", user, course);
    }

    @Override
    public void removeStudentFromCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));
        validateUserAndCourseToRemoveFromCourse(user, course, force);
        Set<User> registeredStudents = course.getStudents();
        if (registeredStudents.remove(user)) {
            wrapJPACall(() -> hometaskSolutionRepository.deleteByStudentIdCourseId(studentId, courseId));
        }
        logger.debug("{} unregistered from curse {}", user, course);
        wrapJPACall(() -> courseRepository.save(course));
    }

    @Override
    public List<User> findStudentsForCourse(Integer courseId) {
        Optional<Course> course = wrapJPACallToOptional(() -> courseRepository.findOne(courseId));
        return course.map(Course::getStudents).orElseGet(Collections::emptySet)
                .stream().sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toList());
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
