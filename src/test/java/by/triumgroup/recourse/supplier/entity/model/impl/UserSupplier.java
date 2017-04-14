package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class UserSupplier implements EntityIntegerPKSupplier<User> {
    @Override
    public User getValidEntityWithoutId() {
        User user = new User();
        user.setName("Ivan");
        user.setSurname("Shimko");
        user.setRole(User.Role.ORGANIZER);
        user.setEmail("a@b.com");
        user.setGender(User.Gender.MALE);
        return user;
    }

    @Override
    public User getInvalidEntity() {
        return new User();
    }
}
