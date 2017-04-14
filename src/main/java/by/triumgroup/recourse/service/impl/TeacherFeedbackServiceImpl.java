package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.TeacherFeedbackRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class TeacherFeedbackServiceImpl
        extends AbstractCrudService<TeacherFeedback, Integer>
        implements TeacherFeedbackService {

    private final TeacherFeedbackRepository repository;
    private final UserRepository userRepository;

    public TeacherFeedbackServiceImpl(TeacherFeedbackRepository repository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<TeacherFeedback>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdOrderByIdDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<TeacherFeedback>> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.STUDENT))
                ? repository.findByStudentIdOrderByIdDesc(id, pageable)
                : null
        );
    }
}
