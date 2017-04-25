package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.util.Collections;

public class UserSupplier implements EntityIntegerPKSupplier<User> {
    @Override
    public User getValidEntityWithoutId() {
        User user = new User();
        user.setName("Ivan");
        user.setSurname("Shimko");
        user.setRole(User.Role.TEACHER);
        user.setEmail("a@b.com");
        user.setGender(User.Gender.MALE);
        user.setCourses(Collections.emptySet());
        return user;
    }

    @Override
    public User getInvalidEntity() {
        return new User();
    }

    public User getWithRole(User.Role role) {
        User result = getValidEntityWithId();
        result.setRole(role);
        return result;
    }
}
