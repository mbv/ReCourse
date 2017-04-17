package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.MainConfiguration;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.exception.RestExceptionHandler;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.util.TestUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = MainConfiguration.class)
public abstract class AbstractControllerTest {
    private MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(getController())
                .setControllerAdvice(new RestExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver(), new AuthenticationPrincipalArgumentResolver())
                .alwaysDo(print())
                .build();
    }

    protected abstract Object getController();

    protected ResultActions sendDelete(String urlTemplate, User user, Object... urlParams) throws Exception {
        return send(delete(urlTemplate, urlParams), user);
    }

    protected ResultActions sendDelete(String urlTemplate, Object... urlParams) throws Exception {
        return sendDelete(urlTemplate, null, urlParams);
    }

    protected ResultActions sendPost(String urlTemplate, Object content, Object... urlParams) throws Exception {
        return sendPost(urlTemplate, content, null, urlParams);
    }

    protected ResultActions sendPost(String urlTemplate, Object content, User user, Object... urlParams) throws Exception {
        return send(post(urlTemplate, urlParams).content(TestUtil.toJson(content)), user);
    }

    protected ResultActions sendGet(String urlTemplate, User user, Object... urlParams) throws Exception {
        return send(get(urlTemplate, urlParams), user);
    }

    protected ResultActions sendGet(String urlTemplate, Object... urlParams) throws Exception {
        return sendGet(urlTemplate, null, urlParams);
    }

    protected ResultActions sendPut(String urlTemplate, Object content, User user, Object... urlParams) throws Exception {
        return send(put(urlTemplate, urlParams).content(TestUtil.toJson(content)), user);

    }

    protected ResultActions sendPut(String urlTemplate, Object content, Object... urlParams) throws Exception {
        return sendPut(urlTemplate, content, null, urlParams);
    }

    private ResultActions send(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions send(MockHttpServletRequestBuilder builder, User user) throws Exception {
        if (user != null){
            return send(builder.with(user(new UserAuthDetails(user))));
        } else {
            return send(builder);
        }

    }
}
