package by.triumgroup.recourse.util;

import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;

public class RoleUtil {

    public static boolean ifExistsWithRole(UserRepository repository, Integer id, User.Role role) {
        User user = repository.findOne(id);
        return (user != null) && (user.getRole() == role);
    }

}
