package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/report")
public interface StudentReportController extends CrudController<StudentReport, Integer> {
}
