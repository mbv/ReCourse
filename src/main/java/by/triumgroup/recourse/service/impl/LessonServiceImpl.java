package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class LessonServiceImpl
        extends AbstractCrudService<Lesson, Integer>
        implements LessonService {

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
        throw new NotImplementedException();
    }

    public Optional<Lesson> update(Lesson entity, Integer id, User.Role performerRole) {
        Optional<Lesson> result;
        Optional<Lesson> databaseLessonOptional = wrapJPACallToOptional(() -> repository.findOne(id));
        if (databaseLessonOptional.isPresent()) {
            entity.setId(id);
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
                ? repository.findByCourseIdOrderByStartTimeDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdOrderByStartTimeDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(courseId) && ifExistsWithRole(userRepository, teacherId, User.Role.TEACHER))
                ? repository.findByTeacherIdAndCourseIdOrderByStartTimeDesc(teacherId, courseId, pageable)
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
                new UserRoleValidator<>(studentFieldInfo, userRepository),
                lessonTimeValidator
        );
    }
}
