package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.TeacherFeedback;
import by.triumgroup.recourse.repository.TeacherFeedbackRepository;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class TeacherFeedbackServiceImpl
        extends AbstractCrudService<TeacherFeedback, Integer>
        implements TeacherFeedbackService {

    private final TeacherFeedbackRepository repository;

    public TeacherFeedbackServiceImpl(TeacherFeedbackRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<TeacherFeedback> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTeacherIdOrderByIdDesc(id, pageable));
    }

    @Override
    public List<TeacherFeedback> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStudentIdOrderByIdDesc(id, pageable));
    }
}
