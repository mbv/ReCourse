package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class CrudControllerTest<E extends BaseEntity<ID>, ID> extends AbstractControllerTest {
    private String idRequest;

    private String generalRequest;

    @Before
    public void initUrls() {
        this.idRequest = String.format("/%s/{id}", getEntityName());
        this.generalRequest = String.format("/%s/", getEntityName());
    }

    protected abstract CrudService<E,ID> getService();

    protected abstract String getEntityName();

    protected abstract EntitySupplier<E,ID> getEntitySupplier();

    @Test
    public void getExistingEntityTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithId();
        when(getService().findById(any())).thenReturn(Optional.of(entity));

        getEntityById(entity.getId())
            .andExpect(status().isOk());
    }

    @Test
    public void getNotExistingEntityTest() throws Exception {
        when(getService().findById(any())).thenReturn(Optional.empty());

        getEntityById(getEntitySupplier().getAnyId())
            .andExpect(status().isNotFound());
    }

    @Test
    public void createValidEntityTest() throws Exception {
        when(getService().add(any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        postEntity(getEntitySupplier().getValidEntityWithoutId())
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

        putEntityById(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEntityValidDataTest() throws Exception {
        when(getService().update(any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityById(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
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

        deleteEntityById(getEntitySupplier().getAnyId()).
                andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        when(getService().delete(any())).thenReturn(Optional.empty());
        deleteEntityById(getEntitySupplier().getAnyId()).
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

}
