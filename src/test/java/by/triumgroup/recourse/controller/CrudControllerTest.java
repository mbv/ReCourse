package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static by.triumgroup.recourse.util.Util.allItemsPage;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class CrudControllerTest<E extends BaseEntity<ID>, ID> extends AbstractControllerTest {
    protected String idRequest;
    protected String generalRequest;

    protected UserSupplier userSupplier = new UserSupplier();

    @Before
    public void initUrls() {
        this.idRequest = String.format("/api/%s/{id}", getEntityName());
        this.generalRequest = String.format("/api/%s/", getEntityName());
    }

    protected abstract CrudService<E,ID> getService();

    protected abstract String getEntityName();

    protected abstract EntitySupplier<E,ID> getEntitySupplier();

    @Test
    public void getExistingEntityTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithId();
        when(getService().findById(any())).thenReturn(Optional.of(entity));

        getEntityByIdAuthorized(entity.getId(), entity)
            .andExpect(status().isOk());
    }

    @Test
    public void getNotExistingEntityTest() throws Exception {
        when(getService().findById(any())).thenReturn(Optional.empty());

        getEntityById(getEntitySupplier().getAnyId())
            .andExpect(status().isNotFound());
    }

    @Test
    public void getAllEntitiesTest() throws Exception {
        when(getService().findAll(allItemsPage())).thenReturn(Lists.emptyList());

        sendGet(generalRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void createValidEntityTest() throws Exception {
        when(getService().add(any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        postEntityAuthorized(getEntitySupplier().getValidEntityWithoutId())
            .andExpect(status().isOk());
    }

    @Test
    public void createInvalidEntityTest() throws Exception {
        postEntity(getEntitySupplier().getInvalidEntity())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateNotExistingEntityTest() throws Exception {
        when(getService().update(any(), any())).thenReturn(Optional.empty());

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEntityValidDataTest() throws Exception {
        when(getService().update(any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
            .andExpect(status().isOk());
    }

    @Test
    public void updateEntityInvalidDataTest() throws Exception {
        putEntityById(getEntitySupplier().getAnyId(), getEntitySupplier().getInvalidEntity())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteExistingEntityTest() throws Exception {
        when(getService().delete(any())).thenReturn(Optional.of(true));
        E entity = getEntitySupplier().getValidEntityWithId();
        deleteEntityByIdAuthorized(entity.getId(), entity).
                andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        when(getService().delete(any())).thenReturn(Optional.empty());
        E entity = getEntitySupplier().getValidEntityWithId();
        deleteEntityByIdAuthorized(entity.getId(), entity).
                andExpect(status().isNotFound());
    }

    protected ResultActions deleteEntityById(ID id) throws Exception {
        return sendDelete(idRequest, id);
    }

    protected ResultActions postEntity(E entity) throws Exception {
        return sendPost(generalRequest, entity);
    }

    protected ResultActions getEntityById(ID id) throws Exception {
        return sendGet(idRequest, id);
    }

    protected ResultActions putEntityById(ID id, E entity) throws Exception {
        return sendPut(idRequest, entity, id);
    }

    protected ResultActions deleteEntityByIdAuthorized(ID id, E entity) throws Exception {
        when(getService().findById(id)).thenReturn(Optional.of(entity));
        User user = prepareAuthorizedUser(entity);
        return sendDelete(idRequest, user, id);
    }

    protected ResultActions postEntityAuthorized(E entity) throws Exception {
        User user = prepareAuthorizedUser(entity);
        return sendPost(generalRequest, entity, user);
    }

    protected ResultActions getEntityByIdAuthorized(ID id, E entity) throws Exception {
        User user = prepareAuthorizedUser(entity);
        return sendGet(idRequest, user, id);
    }

    protected ResultActions putEntityByIdAuthorized(ID id, E entity) throws Exception {
        User user = prepareAuthorizedUser(entity);
        return sendPut(idRequest, entity, user, id);
    }

    private User prepareAuthorizedUser(E entity){
        return prepareAuthorizedUser(entity, userSupplier.getValidEntityWithId());
    }

    protected abstract User prepareAuthorizedUser(E entity, User validUserWithId);
}
