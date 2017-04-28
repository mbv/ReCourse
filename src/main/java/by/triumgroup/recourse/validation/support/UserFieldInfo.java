package by.triumgroup.recourse.validation.support;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.User;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class UserFieldInfo<E extends BaseEntity<ID>, ID> {
    private final Function<E, User> userFieldGetter;
    private final String fieldName;
    private List<User.Role> allowedRoles;

    public UserFieldInfo(Function<E, User> userFieldGetter, String fieldName, List<User.Role> allowedRoles) {
        this.userFieldGetter = userFieldGetter;
        this.fieldName = fieldName;
        this.allowedRoles = allowedRoles;
    }

    public UserFieldInfo(Function<E, User> userFieldGetter, String fieldName, User.Role allowedRole) {
        this(userFieldGetter, fieldName, Collections.singletonList(allowedRole));
    }

    public Function<E, User> getUserFieldGetter(){
        return userFieldGetter;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<User.Role> getAllowedRoles() {
        return allowedRoles;
    }
}
