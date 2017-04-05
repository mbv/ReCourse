package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.validation.Errors;

public interface UserService extends CrudService<User, Integer> {

    User findByEmail(String email) throws ServiceException;

    void register(RegistrationDetails registrationDetails, Errors result) throws ServiceException;
}
