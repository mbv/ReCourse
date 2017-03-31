package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Long> {

    List<HometaskSolution> findByHometaskId(Long id);

    List<HometaskSolution> findByHometaskId(Long id, Pageable pageable);

    List<HometaskSolution> findByStudentId(Long id);

    List<HometaskSolution> findByStudentId(Long id, Pageable pageable);

}
