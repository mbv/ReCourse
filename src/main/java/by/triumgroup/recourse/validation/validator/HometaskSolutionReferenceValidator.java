package by.triumgroup.recourse.validation.validator;


import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class HometaskSolutionReferenceValidator implements Validator {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public HometaskSolutionReferenceValidator(LessonRepository lessonRepository,
                                              UserRepository userRepository,
                                              CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return HometaskSolution.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HometaskSolution entity = (HometaskSolution) o;
        if (entity.getStudent() != null) {
            Optional<Lesson> lesson = wrapJPACallToOptional(() -> lessonRepository.findOne(entity.getLessonId()));
            Optional<User> user = wrapJPACallToOptional(() -> userRepository.findOne(entity.getStudent().getId()));
            if (lesson.isPresent() && user.isPresent()) {
                Optional<Course> course = wrapJPACallToOptional(() -> courseRepository.findOne(lesson.get().getCourseId()));
                if (course.isPresent() && !course.get().getStudents().contains(user.get())) {
                    errors.rejectValue("student", "student", "Student does not registered on this course");
                }
            } else {
                if (!user.isPresent()) {
                    errors.rejectValue("student", "student", "Such student does not exist");
                }
                if (!lesson.isPresent()) {
                    errors.rejectValue("lessonId", "lessonId", "Lesson with such id does not exist");
                }
            }
        } else {
            errors.rejectValue("student", "student", "Student is null");
        }
    }
}
