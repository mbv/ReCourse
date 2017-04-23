package by.triumgroup.recourse.validation.validator;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.repository.CourseRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class CourseTeacherValidator implements Validator {
    private CourseRepository courseRepository;

    public CourseTeacherValidator(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Course.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;
        if (course.getId() != null){
            Course oldCourse = courseRepository.findOne(course.getId());
            if (!Objects.equals(course.getTeacher().getId(), oldCourse.getTeacher().getId())) {
                if (!courseRepository.canUpdateTeacher(course.getId(), course.getTeacher().getId())){
                    errors.rejectValue("teacher", "Can not update teacher, teacher is busy for requested time.");
                }
            }
        }
    }

}
