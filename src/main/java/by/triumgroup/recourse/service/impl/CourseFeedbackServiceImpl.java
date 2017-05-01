package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.validation.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class CourseFeedbackServiceImpl
        extends AbstractCrudService<CourseFeedback, Integer>
        implements CourseFeedbackService {

    private final CourseFeedbackRepository repository;
    private final CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseFeedbackServiceImpl(CourseFeedbackRepository repository, CourseRepository courseRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<CourseFeedback>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseIdOrderByIdDesc(id, pageable)
                : null
        );
    }

    @Override
    protected void validateNestedEntities(CourseFeedback entity) {
        if (entity.getStudent().getId() == null) {
            throw new ServiceBadRequestException("student.id", "Student ID is not specified");
        }
    }

    @Override
    protected String getEntityName() {
        return "course feedback";
    }


    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                User.Role.STUDENT
        );
        return Collections.singletonList(new UserRoleValidator<>(studentFieldInfo, userRepository, repository));
    }
}
