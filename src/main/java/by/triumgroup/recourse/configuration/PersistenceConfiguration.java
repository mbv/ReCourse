package by.triumgroup.recourse.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("by.triumgroup.recourse.repository")
@EntityScan("by.triumgroup.recourse.entity")
public class PersistenceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


}
