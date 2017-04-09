package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.HometaskSolution;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.service.HometaskSolutionService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class HometaskSolutionServiceImpl
        extends AbstractCrudService<HometaskSolution, Integer>
        implements HometaskSolutionService {

    private final HometaskSolutionRepository repository;

    public HometaskSolutionServiceImpl(HometaskSolutionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<HometaskSolution> findByHometaskId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByHometaskId(id, pageable));
    }

    @Override
    public List<HometaskSolution> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStudentId(id, pageable));
    }

    @Override
    public Optional<HometaskSolution> findByStudentIdAndHometaskId(Integer studentId, Integer hometaskId) {
        return wrapJPACallToOptional(() -> repository.findByStudentIdAndHometaskId(studentId, hometaskId));
    }
}
