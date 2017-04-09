package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.Mark;
import by.triumgroup.recourse.repository.MarkRepository;
import by.triumgroup.recourse.service.MarkService;

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
}
