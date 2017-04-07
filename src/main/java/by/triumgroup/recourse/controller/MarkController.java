package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.Mark;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hometask/solution/mark")
public interface MarkController extends CrudController<Mark, Integer> {
}
