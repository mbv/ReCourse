package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.exception.ServiceException;

public interface UserService extends CrudService<User, Integer> {

    User findByEmail(String email) throws ServiceException;

}
