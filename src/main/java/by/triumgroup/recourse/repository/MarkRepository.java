package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Mark;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarkRepository extends PagingAndSortingRepository<Mark, Integer> {

    Mark findBySolutionId(Integer id);

}
