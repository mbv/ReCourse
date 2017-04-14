package by.triumgroup.recourse.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.util.List;

@Configuration
public class PageRequestConfiguration extends WebMvcConfigurerAdapter {

    private PageableHandlerMethodArgumentResolver resolver;

    @Inject
    public PageRequestConfiguration(PageableHandlerMethodArgumentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}

