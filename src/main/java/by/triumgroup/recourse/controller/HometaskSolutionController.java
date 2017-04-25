package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Mark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hometasks/solutions")
public interface HometaskSolutionController extends CrudController<HometaskSolution, Integer> {

    @GetMapping("{solutionId}/mark")
    Mark getMark(@PathVariable("solutionId") Integer solutionId);

}
