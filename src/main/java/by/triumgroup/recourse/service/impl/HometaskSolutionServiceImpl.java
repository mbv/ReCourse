package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.HometaskRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.HometaskSolutionService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class HometaskSolutionServiceImpl
        extends AbstractCrudService<HometaskSolution, Integer>
        implements HometaskSolutionService {

    private final HometaskSolutionRepository repository;
    private final HometaskRepository hometaskRepository;
    private final UserRepository userRepository;

    public HometaskSolutionServiceImpl(HometaskSolutionRepository repository, HometaskRepository hometaskRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.hometaskRepository = hometaskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<HometaskSolution>> findByHometaskId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (hometaskRepository.exists(id))
                ? repository.findByHometaskId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<HometaskSolution>> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.STUDENT))
                ? repository.findByStudentId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<HometaskSolution> findByStudentIdAndHometaskId(Integer studentId, Integer hometaskId) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, studentId, User.Role.STUDENT) && hometaskRepository.exists(hometaskId))
                ? repository.findByStudentIdAndHometaskId(studentId, hometaskId)
                : null
        );
    }
}
