package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public abstract class CrudServiceTest<E extends BaseEntity<ID>, ID extends Serializable> {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Captor
    private ArgumentCaptor<E> captor;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
    }

    protected abstract CrudService<E, ID> getCrudService();

    protected abstract CrudRepository<E, ID> getCrudRepository();

    protected abstract EntitySupplier<E, ID> getEntitySupplier();

    @Test
    public void findExistingEntityTest() throws Exception {
        E expectedEntity = getEntitySupplier().getValidEntityWithId();
        ID id = expectedEntity.getId();
        when(getCrudRepository().findOne(id)).thenReturn(expectedEntity);

        Optional<E> actualResult = getCrudService().findById(id);

        verify(getCrudRepository(), times(1)).findOne(id);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void findNotExistingEntityTest() throws Exception {
        ID id = getEntitySupplier().getAnyId();
        when(getCrudRepository().findOne(id)).thenReturn(null);

        Optional<E> entity = getCrudService().findById(id);

        verify(getCrudRepository(), times(1)).findOne(id);
        Assert.assertFalse(entity.isPresent());
    }

    @Test
    public void findAllEntitiesTest() throws Exception {
        when(getCrudRepository().findAll()).thenReturn(Lists.newArrayList(getEntitySupplier().getValidEntityWithId()));

        List<E> list = Lists.newArrayList(getCrudService().findAll());

        verify(getCrudRepository(), times(1)).findAll();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void addValidEntityTest() throws Exception {
        E expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);

        Optional<E> actualResult = getCrudService().add(expectedEntity);

        verify(getCrudRepository(), times(1)).save(expectedEntity);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void addEntityWithExistingIdTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithId();
        when(getCrudRepository().save(entity)).thenReturn(entity);

        getCrudService().add(entity);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(entity);
        Assert.assertNull(captor.getValue().getId());
    }

    @Test
    public void addInvalidEntityTest() throws Exception {
        when(getCrudRepository().save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));

        thrown.expect(ServiceException.class);

        getCrudService().add(getEntitySupplier().getInvalidEntity());
    }

    @Test
    public void updateEntityWithoutIdTest() throws Exception {
        E expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        ID parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);

        Optional<E> actualResult = getCrudService().update(expectedEntity, parameterId);

        verify(getCrudRepository()).save(captor.capture());
        verifyCallsForUpdate();
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<ID, ID> ids = getEntitySupplier().getDifferentIds();
        ID entityId = ids.getFirst();
        ID parameterId = ids.getSecond();
        E expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        expectedEntity.setId(entityId);
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);

        Optional<E> actualResult = getCrudService().update(expectedEntity, parameterId);

        verify(getCrudRepository()).save(captor.capture());
        verifyCallsForUpdate();
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateInvalidEntityTest() throws Exception {
        when(getCrudRepository().save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);

        thrown.expect(ServiceException.class);

        getCrudService().update(getEntitySupplier().getInvalidEntity(), getEntitySupplier().getAnyId());

        verifyCallsForUpdate();
    }

    @Test
    public void deleteExistingEntityTest() throws Exception {
        getCrudService().delete(getEntitySupplier().getAnyId());

        verify(getCrudRepository(), times(1)).delete(Matchers.<ID>any());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(getCrudRepository()).delete(Matchers.<ID>any());

        Optional<Boolean> actual = getCrudService().delete(getEntitySupplier().getAnyId());

        verify(getCrudRepository(), times(1)).delete(Matchers.<ID>any());
        Assert.assertFalse(actual.isPresent());
    }

    private void verifyCallsForUpdate(){
        verify(getCrudRepository(), times(1)).exists(any());
        verify(getCrudRepository(), times(1)).save(Matchers.<E>any());
    }
}