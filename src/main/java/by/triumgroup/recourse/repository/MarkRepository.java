package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Mark;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarkRepository extends PagingAndSortingRepository<Mark, Long> {

    Mark findBySolutionId(Long id);

}
