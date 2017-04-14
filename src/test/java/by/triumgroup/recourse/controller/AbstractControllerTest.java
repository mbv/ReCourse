package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.MainConfiguration;
import by.triumgroup.recourse.controller.exception.RestExceptionHandler;
import by.triumgroup.recourse.util.TestUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(print())
                .build();
    }

    protected abstract Object getController();

    protected ResultActions sendDelete(String urlTemplate, Object... urlParams) throws Exception {
        return mockMvc.perform(delete(urlTemplate, urlParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions sendPost(String urlTemplate, Object content, Object... urlParams) throws Exception {
        return mockMvc.perform(post(urlTemplate, urlParams)
                .content(TestUtil.toJson(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions sendGet(String urlTemplate, Object... urlParams) throws Exception {
        return mockMvc.perform(get(urlTemplate, urlParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions sendPut(String urlTemplate, Object content, Object... urlParams) throws Exception {
        return mockMvc.perform(put(urlTemplate, urlParams)
                .content(TestUtil.toJson(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}
