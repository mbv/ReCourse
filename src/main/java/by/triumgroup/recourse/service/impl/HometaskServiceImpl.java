package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.repository.HometaskRepository;
import by.triumgroup.recourse.service.HometaskService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class HometaskServiceImpl
        extends AbstractCrudService<Hometask, Integer>
        implements HometaskService {

    private final HometaskRepository repository;

    public HometaskServiceImpl(HometaskRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Hometask> findByLessonId(Integer id) {
        return wrapJPACallToOptional(() -> repository.findByLessonId(id));
    }

    @Override
    protected String getEntityName() {
        return "hometask";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
