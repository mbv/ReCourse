package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.repository.HometaskRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSupplier;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class HometaskServiceTest extends CrudServiceTest<Hometask, Integer> {
    private HometaskService hometaskService;
    private HometaskRepository hometaskRepository;
    private HometaskSupplier hometaskSupplier;

    public HometaskServiceTest() {
        hometaskRepository = Mockito.mock(HometaskRepository.class);
        hometaskService = new HometaskServiceImpl(hometaskRepository);
        hometaskSupplier = new HometaskSupplier();
    }

    @Override
    protected CrudService<Hometask, Integer> getCrudService() {
        return hometaskService;
    }

    @Override
    protected CrudRepository<Hometask, Integer> getCrudRepository() {
        return hometaskRepository;
    }

    @Override
    protected EntitySupplier<Hometask, Integer> getEntitySupplier() {
        return hometaskSupplier;
    }

    @Test
    public void findByExistingLessonIdTest() throws Exception {
        when(hometaskRepository.findByLessonId(anyInt())).thenReturn(hometaskSupplier.getValidEntityWithId());

        Optional<Hometask> hometask = hometaskService.findByLessonId(1);

        verify(hometaskRepository, times(1)).findByLessonId(anyInt());
        assertTrue(hometask.isPresent());
    }

    @Test
    public void findByNotExistingLessonIdTest() throws Exception {
        when(hometaskRepository.findByLessonId(anyInt())).thenReturn(null);

        Optional<Hometask> hometask = hometaskService.findByLessonId(1);

        verify(hometaskRepository, times(1)).findByLessonId(anyInt());
        assertFalse(hometask.isPresent());
    }
}
