package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.CourseFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedback/course")
public interface CourseFeedbackService extends CrudService<CourseFeedback, Integer> {

    List<CourseFeedback> findByCourseId(Integer id, Pageable pageable);

}
