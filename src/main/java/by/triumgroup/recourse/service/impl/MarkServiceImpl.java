package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.repository.MarkRepository;
import by.triumgroup.recourse.service.MarkService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class MarkServiceImpl
        extends AbstractCrudService<Mark, Integer>
        implements MarkService {

    private final MarkRepository repository;

    public MarkServiceImpl(MarkRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Mark> findBySolutionId(Integer id) {
        return wrapJPACallToOptional(() -> repository.findBySolutionId(id));
    }

    @Override
    protected String getEntityName() {
        return "mark";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
