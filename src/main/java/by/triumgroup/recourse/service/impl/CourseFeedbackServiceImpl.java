package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class CourseFeedbackServiceImpl
        extends AbstractCrudService<CourseFeedback, Integer>
        implements CourseFeedbackService {

    private final CourseFeedbackRepository repository;
    private final CourseRepository courseRepository;

    public CourseFeedbackServiceImpl(CourseFeedbackRepository repository, CourseRepository courseRepository) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<List<CourseFeedback>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseIdOrderByIdDesc(id, pageable)
                : null
        );
    }
}
