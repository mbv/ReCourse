package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseService;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.RoleUtil.ifExistsWithRole;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private final CourseRepository repository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository repository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Course> searchByTitle(String title, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTitleContainingIgnoreCaseOrderByIdDesc(title, pageable));
    }

    @Override
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStatusOrderByIdDesc(status, pageable));
    }

    @Override
    protected String getEntityName() {
        return "course";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

}
