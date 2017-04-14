package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HometaskSolutionService extends CrudService<HometaskSolution, Integer> {

    Optional<List<HometaskSolution>> findByHometaskId(Integer id, Pageable pageable);

    Optional<List<HometaskSolution>> findByStudentId(Integer id, Pageable pageable);

    Optional<HometaskSolution> findByStudentIdAndHometaskId(Integer studentId, Integer hometaskId);

}
