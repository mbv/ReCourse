package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Hometask;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HometaskRepository extends PagingAndSortingRepository<Hometask, Integer> {

    Hometask findByLessonId(Integer id);

}
