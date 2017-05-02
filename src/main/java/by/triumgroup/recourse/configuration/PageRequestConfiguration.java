package by.triumgroup.recourse.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class PageRequestConfiguration extends WebMvcConfigurerAdapter {

    public static final int MAX_PAGE_SIZE = 20;
    private PageableHandlerMethodArgumentResolver resolver;

    public PageRequestConfiguration(PageableHandlerMethodArgumentResolver resolver) {
        resolver.setMaxPageSize(MAX_PAGE_SIZE);
        this.resolver = resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}

