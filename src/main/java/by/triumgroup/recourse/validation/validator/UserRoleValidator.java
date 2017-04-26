package by.triumgroup.recourse.validation.validator;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class UserRoleValidator<E extends BaseEntity<ID>, ID extends Serializable> implements Validator {

    private List<UserFieldInfo<E, ID>> allowedRoles;
    private UserRepository userRepository;
    private CrudRepository<E, ID> entityRepository;

    public UserRoleValidator(List<UserFieldInfo<E, ID>> fieldsInfo, UserRepository userRepository, CrudRepository<E, ID> entityRepository) {
        this.allowedRoles = fieldsInfo;
        this.userRepository = userRepository;
        this.entityRepository = entityRepository;
    }

    public UserRoleValidator(UserFieldInfo<E, ID> fieldInfo, UserRepository userRepository, CrudRepository<E, ID> entityRepository) {
        this(Collections.singletonList(fieldInfo), userRepository, entityRepository);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseEntity.class.isAssignableFrom(aClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object o, Errors errors) {
        E newEntity = (E) o;
        E databaseEntity = wrapJPACall(() -> entityRepository.findOne(newEntity.getId()));
        for (UserFieldInfo<E, ID> userFieldInfo : allowedRoles) {
            Integer userId = userFieldInfo.getUserFieldGetter().apply(newEntity).getId();
            User databaseUser = wrapJPACall(() -> userRepository.findOne(userId));
            if (databaseEntity != null){
                Integer oldUserId = userFieldInfo.getUserFieldGetter().apply(databaseEntity).getId();
                if (!oldUserId.equals(userId)){
                    checkUserUpdating(errors, userFieldInfo, databaseUser);
                }
            } else {
                checkUserUpdating(errors, userFieldInfo, databaseUser);
            }
        }
    }

    private void checkUserUpdating(Errors errors, UserFieldInfo<E, ID> userFieldInfo, User databaseUser) {
        if (userFieldInfo.getAllowedRoles().stream().noneMatch(role -> role == databaseUser.getRole())) {
            String fieldName = userFieldInfo.getFieldName();
            errors.rejectValue(fieldName, fieldName, String.format("%s has invalid role", fieldName));
        }
    }
}
