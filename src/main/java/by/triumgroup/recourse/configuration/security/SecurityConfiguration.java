package by.triumgroup.recourse.configuration.security;

import by.triumgroup.recourse.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
@EnableWebSecurity
@Import({
        AuthorizationServerConfiguration.class,
        ResourceServerConfiguration.class,
        MethodSecurityConfiguration.class
})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final int BCRYPT_STRENGTH = 12;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder(BCRYPT_STRENGTH);
        return passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        userDetailsService = new JdbcUserDetailsService(userService);
        return userDetailsService;
    }
}
