package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.Util.ifExistsWithRole;

public class HometaskSolutionServiceImpl
        extends AbstractCrudService<HometaskSolution, Integer>
        implements HometaskSolutionService {

    private final HometaskSolutionRepository repository;
    private final UserRepository userRepository;
    private LessonRepository lessonRepository;

    public HometaskSolutionServiceImpl(HometaskSolutionRepository repository, UserRepository userRepository, LessonRepository lessonRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public <S extends HometaskSolution> Optional<S> add(S entity) {
        return super.add(entity);
    }

    @Override
    public Optional<HometaskSolution> update(HometaskSolution entity, Integer integer) {

        return super.update(entity, integer);
    }

    @Override
    public Optional<List<HometaskSolution>> findByLessonId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (lessonRepository.exists(id))
                ? repository.findByLessonId(id, pageable)
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
    public Optional<HometaskSolution> findByStudentIdAndLessonId(Integer studentId, Integer lessonId) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, studentId, User.Role.STUDENT) && lessonRepository.exists(lessonId))
                ? repository.findByStudentIdAndLessonId(studentId, lessonId)
                : null
        );
    }

    @Override
    protected String getEntityName() {
        return "hometask solution";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<HometaskSolution, Integer> studentFieldInfo = new UserFieldInfo<>(
                HometaskSolution::getStudent,
                "student",
                User.Role.STUDENT
        );
        return Collections.singletonList(new UserRoleValidator<>(studentFieldInfo, userRepository, repository));
    }
}
