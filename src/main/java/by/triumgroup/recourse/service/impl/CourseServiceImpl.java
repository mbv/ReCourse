package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.Course;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class CourseServiceImpl
        extends AbstractCrudService<Course, Integer>
        implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Course> searchByTitle(String title, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTitleContainingIgnoreCaseOrderByIdDesc(title, pageable));
    }

    @Override
    public List<Course> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTeacherIdOrderByIdDesc(id, pageable));
    }

    @Override
    public List<Course> findByTeacherIdAndStatus(Integer id, Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTeacherIdAndStatusOrderByIdDesc(id, status, pageable));
    }

    @Override
    public List<Course> findByOrganizerId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByOrganizerIdOrderByIdDesc(id, pageable));
    }

    @Override
    public List<Course> findByOrganizerIdAndStatus(Integer id, Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByOrganizerIdAndStatusOrderByIdDesc(id, status, pageable));
    }

    @Override
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> {
            List<Course> courses;
            if (status == null) {
                courses = repository.findAllByOrderByIdDesc(pageable);
            } else {
                courses = repository.findByStatusOrderByIdDesc(status, pageable);
            }
            return courses;
        });
    }
}
