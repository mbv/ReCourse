package by.triumgroup.recourse.configuration.security;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public ResourceServerConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/users/register").permitAll()

                .antMatchers(HttpMethod.GET, "/api/courses/**").authenticated()
                .antMatchers( "/api/courses/**").hasAuthority(User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/lessons/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/lessons/**").hasAnyAuthority(User.Role.TEACHER.name(), User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET,"/api/hometasks/solutions/marks/**").authenticated()
                .antMatchers("/api/hometasks/solutions/marks/**").hasAnyAuthority(User.Role.ADMIN.name(), User.Role.TEACHER.name())

                .antMatchers("/api/hometasks/solutions/marked/**").hasAnyAuthority(User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/users/**").authenticated()
                .antMatchers("/api/users/logout", "/api/users/password/change").authenticated()
                .antMatchers( "/api/users/**").hasAuthority(User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/hometasks/solutions/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/hometasks/solutions/**").hasAnyAuthority(User.Role.STUDENT.name(), User.Role.ADMIN.name())
                .antMatchers("/api/hometasks/solutions").hasAuthority(User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/courses/feedbacks/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/courses/feedbacks/**").hasAnyAuthority(User.Role.STUDENT.name(), User.Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/courses/feedbacks/**").hasAnyAuthority(User.Role.STUDENT.name(), User.Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/courses/feedbacks/**").hasAnyAuthority(User.Role.STUDENT.name(), User.Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/teachers/**").hasAnyAuthority(User.Role.TEACHER.name(), User.Role.ADMIN.name())

                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
    }
}
