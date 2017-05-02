package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.exception.*;
import by.triumgroup.recourse.entity.dto.PasswordChanging;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.entity.support.UserRoleEnumConverter;
import by.triumgroup.recourse.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class UserControllerImpl extends AbstractCrudController<User, Integer> implements UserController {

    private static final Logger logger = getLogger(UserControllerImpl.class);
    private UserService userService;
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    public UserControllerImpl(UserService userService, DefaultTokenServices defaultTokenServices) {
        super(userService, logger);
        this.userService = userService;
        this.defaultTokenServices = defaultTokenServices;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.Role.class,
                new UserRoleEnumConverter());
    }

    @Override
    public <S extends User> S create(@Valid @RequestBody S entity, @Auth UserAuthDetails authDetails) {
        throw new MethodNotAllowedException(HttpMethod.POST);
    }

    @Override
    public void delete(Integer integer, @Auth UserAuthDetails authDetails) {
        throw new MethodNotAllowedException(HttpMethod.DELETE);
    }

    @Override
    public User update(@Valid @RequestBody User entity, @PathVariable("id") Integer id, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToRead);
        return wrapServiceCall(logger, () -> {
            Optional<User> callResult = userService.update(entity, id, authDetails);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<User> getUsersByRole(@RequestParam("role") User.Role role, Pageable pageable, @Auth UserAuthDetails authDetails) {
        if (!authDetails.isAdmin()) {
            throw new AccessDeniedException();
        }
        return wrapServiceCall(logger, () -> userService.findByRole(role, pageable));
    }

    @Override
    public void register(@Valid @RequestBody RegistrationDetails registrationDetails) throws ControllerException {
        wrapServiceCall(logger, () -> userService.register(registrationDetails))
                .orElseThrow(BadRequestException::new);
    }

    @Override
    public Iterable<User> getAll(Pageable pageable, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToRead);
        return super.getAll(pageable, authDetails);
    }

    @Override
    public void logout(OAuth2Authentication principal) {
        OAuth2AccessToken accessToken = defaultTokenServices.getAccessToken(principal);
        defaultTokenServices.revokeToken(accessToken.getValue());
    }

    @Override
    public void changePassword(@RequestBody PasswordChanging passwordChanging, @Auth UserAuthDetails userAuthDetails) throws ControllerException {
        wrapServiceCall(logger, () -> userService.changePassword(userAuthDetails.getId(), passwordChanging));
    }

    @Override
    public User getMyInfo(@Auth UserAuthDetails authDetails) {
        return wrapServiceCall(logger, () -> {
                    Optional<User> callResult = userService.findById(authDetails.getId());
                    return callResult.orElseThrow(NotFoundException::new);
                }
        );
    }

    @Override
    protected boolean hasAuthorityToEdit(User entity, UserAuthDetails authDetails) {
        return authDetails.isAdmin();
    }

    @Override
    protected boolean hasAuthorityToRead(User entity, UserAuthDetails authDetails) {
        return authDetails.isAdmin() || (entity != null && authDetails.getId().equals(entity.getId()));
    }
}
