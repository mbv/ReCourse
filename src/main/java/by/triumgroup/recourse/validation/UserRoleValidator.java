//package by.triumgroup.recourse.validator;
//
//import by.triumgroup.recourse.entity.model.BaseEntity;
//import by.triumgroup.recourse.entity.model.User;
//import by.triumgroup.recourse.entity.model.User.Role;
//import by.triumgroup.recourse.service.UserService;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.util.Arrays;
//import java.util.Map;
//
//public class UserRoleValidator<E extends BaseEntity<ID>, ID> implements Validator {
//
//    private Map<UserFieldInfo<E, ID>, Role[]> allowedRoles;
//    private UserService userService;
//
//    public UserRoleValidator(Map<UserFieldInfo<E, ID>, Role[]> allowedRoles, UserService userService) {
//        this.allowedRoles = allowedRoles;
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return BaseEntity.class.isAssignableFrom(aClass);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public void validate(Object o, Errors errors) {
//        E entity = (E) o;
//        allowedRoles.forEach((userFieldInfo, allowedRoles) -> {
//            Integer id = userFieldInfo.getUserFieldGetter().apply(entity).getId();
//            User user = userService.findById(id);
//            if (Arrays.stream(allowedRoles).noneMatch(role -> role == user.getRole())) {
//                String fieldName = userFieldInfo.getFieldName();
//                errors.rejectValue(fieldName, fieldName, String.format("%s has invalid role", fieldName));
//            }
//        });
//    }
//}
