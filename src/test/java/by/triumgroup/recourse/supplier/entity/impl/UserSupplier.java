package by.triumgroup.recourse.supplier.entity.impl;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.supplier.entity.EntitySupplier;
import org.springframework.data.util.Pair;

public class UserSupplier implements EntitySupplier<User,Integer> {
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

    @Override
    public Integer getAnyId() {
        return 1;
    }

    @Override
    public Pair<Integer, Integer> getDifferentIds() {
        return Pair.of(1, 2);
    }
}
