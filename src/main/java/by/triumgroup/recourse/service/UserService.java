package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface UserService extends CrudService<User, Integer> {

    Optional<User> findByEmail(String email) throws ServiceException;

    <S extends User> Optional<S> update(S entity, Integer userId, User performer) throws ServiceException;

    Optional<Boolean> register(RegistrationDetails registrationDetails) throws ServiceException;
}
