package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.MethodNotAllowedException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.dto.RegistrationDetails;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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

    @Override
    public <S extends User> S create(S entity, UserAuthDetails authDetails) {
        throw new MethodNotAllowedException();
    }

    @Override
    public void delete(Integer integer, UserAuthDetails authDetails) {
        throw new MethodNotAllowedException();
    }

    @Override
    public <S extends User> S update(S entity, Integer id, UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToRead);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = userService.update(entity, id, authDetails);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void register(@Valid @RequestBody RegistrationDetails registrationDetails) throws ControllerException {
        try {
            userService.register(registrationDetails).orElseThrow(BadRequestException::new);
        } catch (ServiceException e){
            logger.warn("Error while user registration", e);
            throw new ControllerException(e);
        }
    }

    @Override
    public Iterable<User> getAll(@Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToRead);
        return super.getAll(authDetails);
    }

    @Override
    public void logout(OAuth2Authentication principal) {
        OAuth2AccessToken accessToken = defaultTokenServices.getAccessToken(principal);
        defaultTokenServices.revokeToken(accessToken.getValue());
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
