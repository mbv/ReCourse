package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Integer> {

    List<HometaskSolution> findByHometaskId(Integer id);

    List<HometaskSolution> findByHometaskId(Integer id, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer id);

    List<HometaskSolution> findByStudentId(Integer id, Pageable pageable);

}
