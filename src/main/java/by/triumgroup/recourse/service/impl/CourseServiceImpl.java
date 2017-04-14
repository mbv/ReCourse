package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
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
    public Optional<List<Course>> findByOrganizerId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.ORGANIZER))
                ? repository.findByOrganizerIdOrderByIdDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Course>> findByOrganizerIdAndStatus(Integer id, Course.Status status, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.ORGANIZER))
                ? repository.findByOrganizerIdAndStatusOrderByIdDesc(id, status, pageable)
                : null
        );
    }

    @Override
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStatusOrderByIdDesc(status, pageable));
    }
}
