package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRole(User.Role role, Pageable pageable);

}
