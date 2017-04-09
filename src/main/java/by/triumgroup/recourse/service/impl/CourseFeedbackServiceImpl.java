package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.CourseFeedback;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class CourseFeedbackServiceImpl
        extends AbstractCrudService<CourseFeedback, Integer>
        implements CourseFeedbackService {

    private final CourseFeedbackRepository repository;

    public CourseFeedbackServiceImpl(CourseFeedbackRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<CourseFeedback> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByCourseIdOrderByIdDesc(id, pageable));
    }
}
