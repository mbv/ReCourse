package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/feedback")
public interface TeacherFeedbackController extends CrudController<TeacherFeedback, Integer> {

}
