package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public interface CourseController extends CrudController<Course, Integer> {

    @GetMapping("{courseId}/lessons")
    List<Lesson> getLessons(
            @PathVariable("courseId") Integer courseId, Pageable pageable);

    @GetMapping("{courseId}/feedbacks")
    List<CourseFeedback> getFeedbacks(
            @PathVariable("courseId") Integer courseId, Pageable pageable);

    @GetMapping(value = "search", params = "title")
    List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable);

    @GetMapping(value = "search", params = "status")
    List<Course> searchByStatus(@RequestParam("status") Course.Status status, Pageable pageable);

    @PostMapping("{courseId}/registration")
    @ResponseStatus(HttpStatus.OK)
    void registerToCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails);

    @DeleteMapping("{courseId}/registration")
    @ResponseStatus(HttpStatus.OK)
    void unregisterFromCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails);

    @PostMapping(value = "{courseId}/registration", params = "studentId")
    @ResponseStatus(HttpStatus.OK)
    void registerStudentToCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails);

    @DeleteMapping(value = "{courseId}/registration", params = "studentId")
    @ResponseStatus(HttpStatus.OK)
    void unregisterStudentFromCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails);

}
