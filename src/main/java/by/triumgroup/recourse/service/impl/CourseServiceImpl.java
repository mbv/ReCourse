package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseService;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
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
    public List<Course> findByStatus(Course.Status status, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStatusOrderByIdDesc(status, pageable));
    }

    @Override
    public boolean registerStudentToCourse(Integer studentId, Course courseId) {
//        Set<User> registeredStudents = course.getStudents();
//        registeredStudents.add(student);
//        wrapJPACall(() -> repository.save(course))
        throw new NotImplementedException();
    }

    @Override
    public boolean removeStudentFromCourse(Integer studentId, Course courseId) {
        throw new NotImplementedException();
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
