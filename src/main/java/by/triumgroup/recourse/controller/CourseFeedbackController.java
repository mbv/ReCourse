package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses/feedbacks")
public interface CourseFeedbackController extends CrudController<CourseFeedback, Integer> {

}
