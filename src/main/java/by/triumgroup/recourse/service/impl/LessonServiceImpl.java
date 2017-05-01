package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public Optional<Lesson> update(Lesson entity, Integer integer) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    @Override
    protected void validateNestedEntities(Lesson entity) {
        if (entity.getTeacher().getId() == null) {
            throw new ServiceBadRequestException("teacher.id", "Teacher ID is not specified");
        }
    }

    public Optional<Lesson> update(Lesson entity, Integer id, User.Role performerRole) {
        Optional<Lesson> result;
        Optional<Lesson> databaseLessonOptional = wrapJPACallToOptional(() -> repository.findOne(id));
        if (databaseLessonOptional.isPresent()) {
            entity.setId(id);
            validateNestedEntities(entity);
            validateEntity(entity);
            if (performerRole == User.Role.TEACHER){
                String hometask = entity.getTask();
                entity = databaseLessonOptional.get();
                entity.setTask(hometask);
            }
            Lesson finalEntity = entity;
            result = wrapJPACallToOptional(() -> repository.save(finalEntity));
        } else {
            result = Optional.empty();
        }
        return result;
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
        UserFieldInfo<Lesson, Integer> studentFieldInfo = new UserFieldInfo<>(
                Lesson::getTeacher,
                "teacher",
                User.Role.TEACHER
        );
        return Arrays.asList(
                new UserRoleValidator<>(studentFieldInfo, userRepository, repository),
                lessonTimeValidator
        );
    }
}
