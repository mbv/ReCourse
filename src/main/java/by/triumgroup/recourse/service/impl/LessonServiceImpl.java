package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.LessonService;
import org.springframework.data.domain.Pageable;

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

    public LessonServiceImpl(LessonRepository repository, CourseRepository courseRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
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
}
