package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.Hometask;
import by.triumgroup.recourse.entity.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hometask")
public interface HometaskController extends CrudController<Hometask, Integer> {

    @GetMapping("{hometaskId}/solutions")
    List<HometaskSolution> getSolutions(
            @PathVariable("hometaskId") Integer hometaskId,
            Pageable pageable
    );

}
