package by.triumgroup.recourse.validation.validator;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class UserRoleValidator<E extends BaseEntity<ID>, ID> implements Validator {

    private List<UserFieldInfo<E, ID>> allowedRoles;
    private UserRepository userRepository;

    public UserRoleValidator(List<UserFieldInfo<E, ID>> fieldsInfo, UserRepository userRepository) {
        this.allowedRoles = fieldsInfo;
        this.userRepository = userRepository;
    }

    public UserRoleValidator(UserFieldInfo<E, ID> fieldInfo, UserRepository userRepository) {
        this.allowedRoles = Collections.singletonList(fieldInfo);
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseEntity.class.isAssignableFrom(aClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object o, Errors errors) {
        E entity = (E) o;
        for (UserFieldInfo<E, ID> userFieldInfo : allowedRoles) {
            Integer id = userFieldInfo.getUserFieldGetter().apply(entity).getId();
            User user = wrapJPACall(() -> userRepository.findOne(id));
            if (userFieldInfo.getAllowedRoles().stream().noneMatch(role -> role == user.getRole())) {
                String fieldName = userFieldInfo.getFieldName();
                errors.rejectValue(fieldName, fieldName, String.format("%s has invalid role", fieldName));
            }
        }
    }
}
