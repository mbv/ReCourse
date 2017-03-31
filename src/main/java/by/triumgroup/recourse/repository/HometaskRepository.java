package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Hometask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HometaskRepository extends PagingAndSortingRepository<Hometask, Long> {

    List<Hometask> findByLessonId(Long id);

    List<Hometask> findByLessonId(Long id, Pageable pageable);

}
