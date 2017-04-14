package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.StudentReportRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.StudentReportService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class StudentReportServiceImpl
        extends AbstractCrudService<StudentReport, Integer>
        implements StudentReportService {

    private final StudentReportRepository repository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public StudentReportServiceImpl(StudentReportRepository repository, CourseRepository courseRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<StudentReport>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<StudentReport>> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.STUDENT))
                ? repository.findByStudentId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<StudentReport>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseId(id, pageable)
                : null
        );
    }
}
