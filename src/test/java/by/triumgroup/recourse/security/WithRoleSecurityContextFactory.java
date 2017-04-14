package by.triumgroup.recourse.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collection;

public class WithRoleSecurityContextFactory implements WithSecurityContextFactory<WithRole>{
    @Override
    public SecurityContext createSecurityContext(WithRole withRole) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(withRole.value().name());
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
