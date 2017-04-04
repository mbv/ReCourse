package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.MainConfiguration;
import by.triumgroup.recourse.controller.exception.RestExceptionHandler;
import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.supplier.entity.EntitySupplier;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.supplier.bean.TestBeansSupplier;
import by.triumgroup.recourse.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = MainConfiguration.class)
public abstract class CrudControllerTest<E extends BaseEntity<ID>, ID, TController extends CrudController<E, ID>, TService extends CrudService<E, ID>> {
    private EntitySupplier<E, ID> entitySupplier;

    protected MockMvc mockMvc;

    private CrudService<E, ID> crudService;

    private CrudController<E, ID> crudController;

    private String singleEntityRequest;

    private String baseUrlRequest;

    CrudControllerTest(EntitySupplier<E, ID> entitySupplier, TestBeansSupplier<TController, TService> testBeansSupplier, String baseUrl){
        this.entitySupplier = entitySupplier;
        this.singleEntityRequest = String.format("/%s/1", baseUrl);
        this.baseUrlRequest = String.format("/%s/", baseUrl);
        this.crudController = testBeansSupplier.getBeanUnderTest();
        this.crudService = testBeansSupplier.getMockedBean();
    }

    @Before
    public void initTests() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(crudController)
                .setControllerAdvice(new RestExceptionHandler())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void getExistingEntityTest() throws Exception {
        when(crudService.findById(any())).thenReturn(Optional.of(entitySupplier.getValidEntityWithId()));

        getJson(singleEntityRequest)
            .andExpect(status().isOk());
    }

    @Test
    public void getNotExistingEntityTest() throws Exception {
        when(crudService.findById(any())).thenReturn(Optional.empty());

        getJson(singleEntityRequest)
            .andExpect(status().isNotFound());
    }

    @Test
    public void createValidEntityTest() throws Exception {
        when(crudService.add(any())).thenReturn(Optional.of(entitySupplier.getValidEntityWithId()));

        postJson(baseUrlRequest, TestUtil.toJson(entitySupplier.getValidEntityWithoutId()))
            .andExpect(status().isOk());
    }

    @Test
    public void createInvalidEntityTest() throws Exception {
        postJson(baseUrlRequest, TestUtil.toJson(entitySupplier.getInvalidEntity()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateNotExistingEntityTest() throws Exception {
        when(crudService.update(any(), any())).thenReturn(Optional.empty());

        putJson(singleEntityRequest, TestUtil.toJson(entitySupplier.getValidEntityWithoutId()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEntityValidDataTest() throws Exception {
        when(crudService.update(any(), any())).thenReturn(Optional.of(entitySupplier.getValidEntityWithId()));

        putJson(singleEntityRequest, TestUtil.toJson(entitySupplier.getValidEntityWithoutId()))
            .andExpect(status().isOk());
    }

    @Test
    public void updateEntityInvalidDataTest() throws Exception {
        putJson(singleEntityRequest, TestUtil.toJson(entitySupplier.getInvalidEntity()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteExistingEntityTest() throws Exception {
        when(crudService.delete(any())).thenReturn(Optional.of(true));

        deleteJson(singleEntityRequest).
                andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        when(crudService.delete(any())).thenReturn(Optional.empty());
        deleteJson(singleEntityRequest).
                andExpect(status().isNotFound());
    }

    private ResultActions deleteJson(String url) throws Exception {
        return mockMvc.perform(delete(url)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions postJson(String url, String content) throws Exception {
        return mockMvc.perform(post(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions getJson(String url) throws Exception {
        return mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions putJson(String url, String content) throws Exception {
        return mockMvc.perform(put(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}
