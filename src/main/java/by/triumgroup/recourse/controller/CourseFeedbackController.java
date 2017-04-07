package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.CourseFeedback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course/feedback")
public interface CourseFeedbackController extends CrudController<CourseFeedback, Integer> {

}
