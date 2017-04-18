package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToBoolean;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private final CourseRepository repository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository repository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public <S extends Course> Optional<S> update(S newCourse, Integer id) {
        Optional<S> result = Optional.empty();
        boolean canSave = true;
        Optional<Course> existingCourse = wrapJPACallToOptional(() -> repository.findOne(id));
        if (existingCourse.isPresent()) {
            Course oldCourse = existingCourse.get();
            newCourse.setId(id);
            if (isNewTeacher(oldCourse, newCourse)){
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

    private Optional<Boolean> updateTeacher(Course course, User newTeacher){
        return wrapJPACallToBoolean(() -> repository.updateTeacher(course.getId(), newTeacher.getId()));
    }

    private boolean isNewTeacher(Course oldCourse, Course newCourse) {
        return !Objects.equals(newCourse.getTeacher().getId(), oldCourse.getTeacher().getId());
    }
}
