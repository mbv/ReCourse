package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Hometask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HometaskRepository extends PagingAndSortingRepository<Hometask, Integer> {

    List<Hometask> findByLessonId(Integer id);

    List<Hometask> findByLessonId(Integer id, Pageable pageable);

}
