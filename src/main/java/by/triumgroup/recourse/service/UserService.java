package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.dto.PasswordChanging;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User, Integer> {

    List<User> findByRole(User.Role role, Pageable pageable);

    Optional<User> findByEmail(String email) throws ServiceException;

    <S extends User> Optional<S> update(S entity, Integer userId, User performer) throws ServiceException;

    Optional<Boolean> register(RegistrationDetails registrationDetails) throws ServiceException;

    Optional<Boolean> changePassword(Integer userId, PasswordChanging passwordChanging) throws ServiceException;
}
