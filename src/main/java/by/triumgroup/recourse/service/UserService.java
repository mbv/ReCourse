package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

}
