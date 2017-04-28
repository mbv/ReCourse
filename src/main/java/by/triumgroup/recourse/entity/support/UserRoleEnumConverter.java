package by.triumgroup.recourse.entity.support;

import by.triumgroup.recourse.entity.model.User;

import java.beans.PropertyEditorSupport;

public class UserRoleEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(User.Role.valueOf(text.toUpperCase()));
    }
}
