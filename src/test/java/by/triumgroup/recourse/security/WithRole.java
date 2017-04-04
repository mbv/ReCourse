package by.triumgroup.recourse.security;

import by.triumgroup.recourse.entity.User;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithRoleSecurityContextFactory.class)
public @interface WithRole {
    User.Role value();
}
