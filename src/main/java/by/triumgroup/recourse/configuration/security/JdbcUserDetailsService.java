package by.triumgroup.recourse.configuration.security;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

public class JdbcUserDetailsService implements UserDetailsService {


    private final UserService userService;

    JdbcUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            username = URLDecoder.decode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UsernameNotFoundException("Wrong encoding");
        }
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found in database.");
        }
        return new CustomUserDetails(user);

    }

    private static class CustomUserDetails extends User implements UserDetails, Serializable {

        CustomUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList(
                    getRole().name()
            );
        }

        @Override
        public String getPassword() {
            return getPasswordHash();
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return !isDeleted();
        }
    }

}
