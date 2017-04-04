package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.supplier.bean.TestBeansSupplier;
import by.triumgroup.recourse.supplier.entity.EntitySupplier;
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
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public abstract class CrudServiceTest<
    E extends BaseEntity<ID>,
    ID extends Serializable,
    TService extends CrudService<E, ID>,
    TRepository extends PagingAndSortingRepository<E, ID>
    > {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Captor
    private ArgumentCaptor<E> captor;

    private TService crudService;
    private TRepository crudRepository;
    private EntitySupplier<E, ID> entitySupplier;

    protected CrudServiceTest(TestBeansSupplier<TService, TRepository> testBeansSupplier, EntitySupplier<E, ID> entitySupplier) {
        this.crudService = testBeansSupplier.getBeanUnderTest();
        this.crudRepository = testBeansSupplier.getMockedBean();
        this.entitySupplier = entitySupplier;
    }

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findExistingEntityTest() throws Exception {
        E expectedEntity = entitySupplier.getValidEntityWithId();
        ID id = expectedEntity.getId();
        when(crudRepository.findOne(id)).thenReturn(expectedEntity);

        Optional<E> actualResult = crudService.findById(id);

        verify(crudRepository, times(1)).findOne(id);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void findNotExistingEntityTest() throws Exception {
        ID id = entitySupplier.getAnyId();
        when(crudRepository.findOne(id)).thenReturn(null);

        Optional<E> entity = crudService.findById(id);

        verify(crudRepository, times(1)).findOne(id);
        Assert.assertFalse(entity.isPresent());
    }

    @Test
    public void addValidEntityTest() throws Exception {
        E expectedEntity = entitySupplier.getValidEntityWithoutId();
        when(crudRepository.save(expectedEntity)).thenReturn(expectedEntity);

        Optional<E> actualResult = crudService.add(expectedEntity);

        verify(crudRepository, times(1)).save(expectedEntity);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void addEntityWithExistingIdTest() throws Exception {
        E entity = entitySupplier.getValidEntityWithId();
        when(crudRepository.save(entity)).thenReturn(entity);

        crudService.add(entity);

        verify(crudRepository).save(captor.capture());
        verify(crudRepository, times(1)).save(entity);
        Assert.assertNull(captor.getValue().getId());
    }

    @Test
    public void addInvalidEntityTest() throws Exception {
        when(crudRepository.save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));

        thrown.expect(ServiceException.class);

        crudService.add(entitySupplier.getInvalidEntity());
    }

    @Test
    public void updateEntityWithoutIdTest() throws Exception {
        E expectedEntity = entitySupplier.getValidEntityWithoutId();
        ID parameterId = entitySupplier.getAnyId();
        when(crudRepository.save(expectedEntity)).thenReturn(expectedEntity);
        when(crudRepository.exists(parameterId)).thenReturn(true);

        Optional<E> actualResult = crudService.update(expectedEntity, parameterId);

        verify(crudRepository).save(captor.capture());
        verifyCallsForUpdate();
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<ID, ID> ids = entitySupplier.getDifferentIds();
        ID entityId = ids.getFirst();
        ID parameterId = ids.getSecond();
        E expectedEntity = entitySupplier.getValidEntityWithoutId();
        expectedEntity.setId(entityId);
        when(crudRepository.save(expectedEntity)).thenReturn(expectedEntity);
        when(crudRepository.exists(parameterId)).thenReturn(true);

        Optional<E> actualResult = crudService.update(expectedEntity, parameterId);

        verify(crudRepository).save(captor.capture());
        verifyCallsForUpdate();
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateInvalidEntityTest() throws Exception {
        when(crudRepository.save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));
        when(crudRepository.exists(any())).thenReturn(true);

        thrown.expect(ServiceException.class);

        crudService.update(entitySupplier.getInvalidEntity(), entitySupplier.getAnyId());

        verifyCallsForUpdate();
    }

    @Test
    public void deleteExistingEntityTest() throws Exception {
        crudService.delete(entitySupplier.getAnyId());

        verify(crudRepository, times(1)).delete(Matchers.<ID>any());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(crudRepository).delete(Matchers.<ID>any());

        Optional<Boolean> actual = crudService.delete(entitySupplier.getAnyId());

        verify(crudRepository, times(1)).delete(Matchers.<ID>any());
        Assert.assertFalse(actual.isPresent());
    }

    private void verifyCallsForUpdate(){
        verify(crudRepository, times(1)).exists(any());
        verify(crudRepository, times(1)).save(Matchers.<E>any());
    }
}