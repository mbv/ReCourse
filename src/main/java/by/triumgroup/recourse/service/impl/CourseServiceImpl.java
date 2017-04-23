package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.CourseTeacherValidator;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.*;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.*;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private final CourseRepository repository;
    private final UserRepository userRepository;
    private CourseTeacherValidator courseTeacherValidator;

    public CourseServiceImpl(CourseRepository repository, UserRepository userRepository, CourseTeacherValidator courseTeacherValidator) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.courseTeacherValidator = courseTeacherValidator;
    }

    @Override
    public <S extends Course> Optional<S> update(S newCourse, Integer id) {
        Optional<S> result = Optional.empty();
        boolean canSave = true;
        Optional<Course> existingCourse = wrapJPACallToOptional(() -> repository.findOne(id));
        if (existingCourse.isPresent()) {
            Course oldCourse = existingCourse.get();
            newCourse.setId(id);
            validateEntity(newCourse);
            if (!Objects.equals(newCourse.getTeacher().getId(), oldCourse.getTeacher().getId())){
                canSave = updateTeacher(newCourse, newCourse.getTeacher()).isPresent();
            }
            if (canSave){
                result = super.update(newCourse, id);
            }
        }
        return result;
    }

    @Override
    public List<Course> searchByTitle(String title, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTitleContainingIgnoreCaseOrderByIdDesc(title, pageable));
    }

    @Override
    public Optional<List<Course>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdOrderByIdDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Course>> findByTeacherIdAndStatus(Integer id, Course.Status status, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdAndStatusOrderByIdDesc(id, status, pageable)
                : null
        );
    }

    @Override
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStatusOrderByIdDesc(status, pageable));
    }

    @Override
    protected String getEntityName() {
        return "course";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<Course, Integer> teacherFieldInfo = new UserFieldInfo<>(
                Course::getTeacher,
                "teacher",
                User.Role.TEACHER);
        return Arrays.asList(
                new UserRoleValidator<>(teacherFieldInfo, userRepository),
                courseTeacherValidator
        );
    }

    private Optional<Boolean> updateTeacher(Course course, User newTeacher){
        return wrapJPACallToBoolean(() -> repository.updateTeacher(course.getId(), newTeacher.getId()));
    }

}
