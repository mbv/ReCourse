package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lessons")
public interface LessonController extends CrudController<Lesson, Integer> { }
