package by.triumgroup.recourse.security;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WithRoleSecurityContextFactory implements WithSecurityContextFactory<WithRole>{
    @Override
    public SecurityContext createSecurityContext(WithRole withRole) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User.Role[] value = withRole.value();

        List<String> collect = Arrays.stream(value).map(Enum::name).collect(Collectors.toList());
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(collect.toArray(new String[value.length]));
        OAuth2Request request = new OAuth2Request(
                null,
                null,
                authorities,
                true,
                null,
                null,
                null,
                null,
                null
        );
        Authentication auth = new OAuth2Authentication(request, null);
        context.setAuthentication(auth);
        return context;
    }
}
