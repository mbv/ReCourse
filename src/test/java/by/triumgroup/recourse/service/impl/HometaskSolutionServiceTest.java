package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class HometaskSolutionServiceTest extends CrudServiceTest<HometaskSolution, Integer> {
    private CourseRepository courseRepository;
    private LessonRepository lessonRepository;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionRepository hometaskSolutionRepository;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private UserRepository userRepository;
    private UserSupplier userSupplier;

    public HometaskSolutionServiceTest() {
        hometaskSolutionRepository = Mockito.mock(HometaskSolutionRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        lessonRepository = Mockito.mock(LessonRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        hometaskSolutionService = new HometaskSolutionServiceImpl(hometaskSolutionRepository, userRepository, lessonRepository, courseRepository);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
        userSupplier = new UserSupplier();
    }

    @Override
    protected CrudService<HometaskSolution, Integer> getCrudService() {
        return hometaskSolutionService;
    }

    @Override
    protected CrudRepository<HometaskSolution, Integer> getCrudRepository() {
        return hometaskSolutionRepository;
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }


    @Test
    public void findByExistingStudentIdAndHometaskIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.STUDENT);
        HometaskSolution solution = hometaskSolutionSupplier.getValidEntityWithId();
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(lessonRepository.exists(anyInt())).thenReturn(true);
        when(hometaskSolutionRepository.findByStudentIdAndLessonId(anyInt(), any()))
                .thenReturn(solution);

        Optional<HometaskSolution> courses = hometaskSolutionService.findByStudentIdAndLessonId(user.getId(), solution.getId());

        verify(userRepository, times(1)).findOne(anyInt());
        verify(lessonRepository, times(1)).exists(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByStudentIdAndLessonId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotStudentIdAndHometaskIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.TEACHER);
        when(userRepository.findOne(anyInt())).thenReturn(user);

        Optional<HometaskSolution> courses = hometaskSolutionService.findByStudentIdAndLessonId(user.getId(), 1);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingStudentIdTest() throws Exception {
        User user = userSupplier.getValidEntityWithId();
        user.setRole(User.Role.STUDENT);
        when(userRepository.findOne(anyInt())).thenReturn(user);
        when(hometaskSolutionRepository.findByStudentId(anyInt(), any()))
                .thenReturn(Collections.singletonList(hometaskSolutionSupplier.getValidEntityWithId()));

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByStudentId(user.getId(), null);

        verify(userRepository, times(1)).findOne(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByStudentId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotExistingStudentIdTest() throws Exception {
        when(userRepository.findOne(anyInt())).thenReturn(null);

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByStudentId(1, null);

        verify(userRepository, times(1)).findOne(anyInt());
        assertFalse(courses.isPresent());
    }

    @Test
    public void findByExistingHometaskIdTest() throws Exception {
        when(lessonRepository.exists(anyInt())).thenReturn(true);
        when(hometaskSolutionRepository.findByLessonId(anyInt(), any()))
                .thenReturn(Collections.singletonList(hometaskSolutionSupplier.getValidEntityWithId()));

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByLessonId(1, null);

        verify(lessonRepository, times(1)).exists(anyInt());
        verify(hometaskSolutionRepository, times(1)).findByLessonId(anyInt(), any());
        assertTrue(courses.isPresent());
    }

    @Test
    public void findByNotExistingHometaskIdTest() throws Exception {
        when(lessonRepository.exists(anyInt())).thenReturn(false);

        Optional<List<HometaskSolution>> courses = hometaskSolutionService.findByLessonId(1, null);

        verify(lessonRepository, times(1)).exists(anyInt());
        assertFalse(courses.isPresent());
    }

    @Override
    protected void setupAllowedRoles(HometaskSolution entity) {
        Integer studentId = entity.getStudent().getId();
        when(userRepository.findOne(studentId)).thenReturn(userSupplier.getWithRole(User.Role.STUDENT));
    }

}
