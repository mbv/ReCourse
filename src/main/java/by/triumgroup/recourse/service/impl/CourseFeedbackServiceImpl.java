package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class CourseFeedbackServiceImpl
        extends AbstractCrudService<CourseFeedback, Integer>
        implements CourseFeedbackService {

    private final CourseFeedbackRepository courseFeedbackRepository;
    private final CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseFeedbackServiceImpl(CourseFeedbackRepository courseFeedbackRepository, CourseRepository courseRepository, UserRepository userRepository) {
        super(courseFeedbackRepository);
        this.courseFeedbackRepository = courseFeedbackRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public <S extends CourseFeedback> Optional<S> add(S entity) {
        entity.setId(null);
        validateEntity(entity);
        checkCreationAvailability(entity);
        return wrapJPACallToOptional(() -> courseFeedbackRepository.save(entity));
    }

    private <S extends CourseFeedback> void checkCreationAvailability(S entity) {
        Course course = wrapJPACall(() -> courseRepository.findOne(entity.getCourseId()));
        if (course != null) {
            if (course.getStatus() != Course.Status.FINISHED) {
                throw new ServiceBadRequestException("courseId", "Feedbacks can be added only to finished courses");
            }
        } else {
            throw new ServiceBadRequestException("courseId", "Course with such id doesn't exist");
        }
    }

    @Override
    public Optional<List<CourseFeedback>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? courseFeedbackRepository.findByCourseIdOrderByIdDesc(id, pageable)
                : null
        );
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
        return Collections.singletonList(new UserRoleValidator<>(studentFieldInfo, userRepository, courseFeedbackRepository));
    }
}
