package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.exception.ServiceAccessDeniedException;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.Util.ifExistsWithRole;
import static org.slf4j.LoggerFactory.getLogger;

public class LessonServiceImpl
        extends AbstractCrudService<Lesson, Integer>
        implements LessonService {

    private static final Logger logger = getLogger(LessonServiceImpl.class);

    private final LessonRepository repository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private LessonTimeValidator lessonTimeValidator;

    public LessonServiceImpl(LessonRepository repository, CourseRepository courseRepository, UserRepository userRepository, LessonTimeValidator lessonTimeValidator) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonTimeValidator = lessonTimeValidator;
    }

    @Override
    public <S extends Lesson> Optional<S> add(S entity) {
        Course course = wrapJPACall(() -> courseRepository.findOne(entity.getCourseId()));
        User teacher = wrapJPACall(() -> userRepository.findOne(entity.getTeacher().getId()));
        checkUserAndCourseExistence(teacher, course);
        checkCreationAvailability(entity, course);
        validateEntity(entity);
        return wrapJPACallToOptional(() -> repository.save(entity));
    }

    private void checkCreationAvailability(Lesson entity, Course course) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (entity.getStartTime().before(Timestamp.from(Instant.now()))) {
            messages.add(new ErrorMessage("startTime", "Start time must be in future"));
        }
        if (course.getRegistrationEnd().before(entity.getStartTime())) {
            messages.add(new ErrorMessage("startTime", "Start time must be after course registration end"));
        }
        rejectIfNeed(messages);
    }

    private void checkUserAndCourseExistence(User teacher, Course course) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (teacher == null) {
            messages.add(new ErrorMessage("teacher", "Teacher not found"));
        }
        if (course == null) {
            messages.add(new ErrorMessage("courseId", "Course not found"));
        }
        rejectIfNeed(messages);
    }

    private void rejectIfNeed(List<ErrorMessage> messages) {
        if (!messages.isEmpty()) {
            throw new ServiceBadRequestException(messages);
        }
    }

    @Override
    public Optional<Lesson> update(Lesson entity, Integer integer) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    public Optional<Lesson> update(Lesson entity, Integer id, User.Role performerRole) {
        Optional<Lesson> result;
        Optional<Lesson> databaseLesson = wrapJPACallToOptional(() -> repository.findOne(id));
        if (!databaseLesson.isPresent()) {
            return Optional.empty();
        }
        entity.setId(id);
        validateEntity(entity);
        switch (performerRole) {
            case TEACHER:
                prepareTeacherUpdate(entity, databaseLesson.get());
                break;
            case ADMIN:
                if (!databaseLesson.get().getStartTime().equals(entity.getStartTime())
                        && databaseLesson.get().getStartTime().before(Timestamp.from(Instant.now()))) {
                    throw new ServiceBadRequestException("startTime", "Cannot update start time of finished lesson");
                }
                break;
            default:
                logger.error("Denied performer role {}! Normally this message is never shows", performerRole);
                throw new ServiceAccessDeniedException();
        }

        result = wrapJPACallToOptional(() -> repository.save(entity));
        return result;
    }

    private void prepareTeacherUpdate(Lesson entity, Lesson lesson) {
        if (lesson.getStartTime().before(Timestamp.from(Instant.now()))) {
            throw new ServiceBadRequestException("task", "Cannot update task after lesson finish");
        } else {
            entity.setId(lesson.getId());
            entity.setTopic(lesson.getTopic());
            entity.setCourseId(lesson.getCourseId());
            entity.setDuration(lesson.getDuration());
            entity.setStartTime(lesson.getStartTime());
            entity.setTeacher(lesson.getTeacher());
        }
    }


    @Override
    public Optional<List<Lesson>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseIdOrderByStartTimeAsc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdOrderByStartTimeAsc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(courseId) && ifExistsWithRole(userRepository, teacherId, User.Role.TEACHER))
                ? repository.findByTeacherIdAndCourseIdOrderByStartTimeAsc(teacherId, courseId, pageable)
                : null
        );
    }

    @Override
    protected String getEntityName() {
        return "lesson";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<Lesson, Integer> teacherFieldInfo = new UserFieldInfo<>(
                Lesson::getTeacher,
                "teacher",
                User.Role.TEACHER
        );
        return Arrays.asList(
                new UserRoleValidator<>(teacherFieldInfo, userRepository, repository),
                lessonTimeValidator
        );
    }
}
