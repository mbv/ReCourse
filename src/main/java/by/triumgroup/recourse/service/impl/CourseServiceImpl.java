package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.*;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.exception.ServiceAccessDeniedException;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
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
    private final CourseFeedbackRepository courseFeedbackRepository;
    private final HometaskSolutionRepository hometaskSolutionRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             LessonRepository lessonRepository,
                             CourseFeedbackRepository courseFeedbackRepository,
                             HometaskSolutionRepository hometaskSolutionRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.courseFeedbackRepository = courseFeedbackRepository;
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
    public List<User> findStudentsForCourse(Integer courseId) {
        Optional<Course> course = wrapJPACallToOptional(() -> courseRepository.findOne(courseId));
        return course.map(Course::getStudents).orElseGet(Collections::emptySet)
                .stream().sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toList());
    }

    @Override
    public <S extends Course> Optional<S> add(S entity) {
        entity.setStatus(Course.Status.DRAFT);
        return super.add(entity);
    }

    @Override
    public Optional<Course> update(Course entity, Integer id) {
        Course course = wrapJPACall(() -> courseRepository.findOne(id));
        if (course == null) {
            return Optional.empty();
        }
        checkUpdateAvailability(entity, course);
        processStatusChangingSideEffects(entity, course);
        return wrapJPACallToOptional(() -> courseRepository.save(entity));
    }

    private void checkUpdateAvailability(Course updatedCourse, Course databaseCourse) {
        updatedCourse.setId(databaseCourse.getId());
        List<ErrorMessage> errors = new ArrayList<>();
        validateMaxStudentsChange(updatedCourse, databaseCourse, errors);
        validateRegistrationEndChange(updatedCourse, databaseCourse, errors);
        rejectIfNeed(errors);
        updatedCourse.setStudents(databaseCourse.getStudents());
    }

    private void validateRegistrationEndChange(Course updatedCourse, Course databaseCourse, List<ErrorMessage> errors) {
        if (!databaseCourse.getRegistrationEnd().equals(updatedCourse.getRegistrationEnd())) {
            Lesson firstLesson = wrapJPACall(() ->
                    lessonRepository.findFirstByCourseIdOrderByStartTimeAsc(databaseCourse.getId()));
            if (firstLesson.getStartTime().before(updatedCourse.getRegistrationEnd())) {
                errors.add(new ErrorMessage("registrationEnd", "Registration end must be before first lesson"));
            }
        }
    }

    private void validateMaxStudentsChange(Course updatedCourse, Course databaseCourse, List<ErrorMessage> errors) {
        if (!databaseCourse.getMaxStudents().equals(updatedCourse.getMaxStudents())) {
            if (databaseCourse.getStatus() == updatedCourse.getStatus()
                    || updatedCourse.getStatus() != Course.Status.DRAFT) {
                if (databaseCourse.getStudents().size() >= updatedCourse.getMaxStudents()) {
                    errors.add(new ErrorMessage("maxStudents",
                            "Too much registered users (" + databaseCourse.getStudents().size() + ")!"));
                }
            }
        }
    }

    private void processStatusChangingSideEffects(Course updatedCourse, Course databaseCourse) {
        if (databaseCourse.getStatus() != updatedCourse.getStatus()) {
            switch (updatedCourse.getStatus()) {
                case DRAFT:
                    Integer deletedSolutions = wrapJPACall(() ->
                            hometaskSolutionRepository.deleteByCourseId(databaseCourse.getId()));
                    Integer deletedFeedbacks = wrapJPACall(() -> courseFeedbackRepository.deleteByCourseId(databaseCourse.getId()));
                    Integer deletedStudents = updatedCourse.getStudents().size();
                    updatedCourse.getStudents().clear();
                    logger.debug("Deleted {} feedbacks and {} solutions, removed {} students from course[{}]",
                            deletedFeedbacks, deletedSolutions, deletedStudents, databaseCourse.getId());
                    break;
                case PUBLISHED:
                    deletedSolutions = wrapJPACall(() -> hometaskSolutionRepository.deleteByCourseId(databaseCourse.getId()));
                    logger.debug("Deleted {} solutions from course[{}]", deletedSolutions, databaseCourse.getId());
                    break;
                case FINISHED:
                    break;
                default:
                    logger.error("Invalid course status {}", updatedCourse.getStatus());
                    throw new ServiceBadRequestException("Invalid course", "Invalid course status");
            }
            logger.debug("Course[{}] shifted from status {} to {}",
                    updatedCourse.getId(), databaseCourse.getStatus(), updatedCourse.getStatus());
        }
    }

    @Override
    public void registerStudentToCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));

        checkUserAndCourseExistence(course, user);
        checkCourseRegistrationAvailability(course, user, force);

        Set<User> registeredStudents = course.getStudents();
        registeredStudents.add(user);
        wrapJPACall(() -> courseRepository.save(course));
        logger.debug("User[{}] registered on course[{}]{}", user.getId(), course.getId(), (force ? " by admin" : " "));
    }

    @Override
    public void removeStudentFromCourse(Integer courseId, Integer studentId, boolean force) {
        Course course = wrapJPACall(() -> courseRepository.findOne(courseId));
        User user = wrapJPACall(() -> userRepository.findOne(studentId));

        checkUserAndCourseExistence(course, user);
        checkUserRemovingFromCourseAvailability(course, user, force);

        Set<User> registeredStudents = course.getStudents();
        if (registeredStudents.remove(user)) {
            Integer deletedSolutions = wrapJPACall(() ->
                    hometaskSolutionRepository.deleteByStudentIdCourseId(studentId, courseId));
            logger.debug("Deleted {} solutions from course[{}]", deletedSolutions, course.getId());
        }
        wrapJPACall(() -> courseRepository.save(course));
        logger.debug("User[{}] unregistered from course[{}]{}", user.getId(), course.getId(), (force ? " by admin" : " "));
    }

    private void checkUserAndCourseExistence(Course course, User user) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (user == null) {
            messages.add(new ErrorMessage("user", "User not found"));
        }
        if (course == null) {
            messages.add(new ErrorMessage("course", "Course not found"));
        }
        rejectIfNeed(messages);
    }

    private void checkCourseRegistrationAvailability(Course course, User user, boolean force) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (user.getRole() == User.Role.STUDENT) {
            switch (course.getStatus()) {
                case DRAFT:
                    if (force) {
                        messages.add(new ErrorMessage("course", "Cannot register students on draft course"));
                    } else {
                        messages.add(new ErrorMessage("course", "Course not found"));
                    }
                    break;
                case PUBLISHED:
                    if (course.getMaxStudents() == course.getStudents().size()) {
                        messages.add(new ErrorMessage("course", "Max students limit has been already reached"));
                    }
                    if (course.getRegistrationEnd().before(Timestamp.from(Instant.now()))) {
                        messages.add(new ErrorMessage("course", "Cannot register after registration end time"));
                    }
                    break;
                case FINISHED:
                    messages.add(new ErrorMessage("course", "Cannot register on finished course"));
                    break;
                default:
                    logger.error("Invalid course status {}", course.getStatus());
                    throw new ServiceBadRequestException("Invalid course", "Invalid course status");
            }
        } else {
            if (force) {
                messages.add(new ErrorMessage("user", "Only students can register on course"));
            } else {
                throw new ServiceAccessDeniedException();
            }
        }
        rejectIfNeed(messages);
    }

    private void checkUserRemovingFromCourseAvailability(Course course, User user, boolean force) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (user.getRole() == User.Role.STUDENT) {
            switch (course.getStatus()) {
                case DRAFT:
                    if (force) {
                        messages.add(new ErrorMessage("course", "Cannot unregister students from draft course"));
                    } else {
                        messages.add(new ErrorMessage("course", "Course not found"));
                    }
                    break;
                case PUBLISHED:
                    break;
                case FINISHED:
                    messages.add(new ErrorMessage("course", "Cannot unregister from finished course"));
                    break;
                default:
                    logger.error("Invalid course status {}", course.getStatus());
                    throw new ServiceBadRequestException("Invalid course", "Invalid course status");
            }
        } else {
            if (force) {
                messages.add(new ErrorMessage("user", "Only students can unregister from course"));
            } else {
                throw new ServiceAccessDeniedException();
            }
        }
        rejectIfNeed(messages);
    }

    private void rejectIfNeed(List<ErrorMessage> messages) {
        if (!messages.isEmpty()) {
            throw new ServiceBadRequestException(messages);
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
