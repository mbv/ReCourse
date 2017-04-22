package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.User;

import java.util.function.Function;

public class UserFieldInfo<E extends BaseEntity<ID>, ID> {
    private final Function<E, User> userFieldGetter;
    private final String fieldName;

    public UserFieldInfo(Function<E, User> userFieldGetter, String fieldName) {
        this.userFieldGetter = userFieldGetter;
        this.fieldName = fieldName;
    }

    public Function<E, User> getUserFieldGetter(){
        return userFieldGetter;
    }

    public String getFieldName() {
        return fieldName;
    }
}
