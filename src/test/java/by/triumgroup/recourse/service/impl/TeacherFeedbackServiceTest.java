package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.TeacherFeedbackRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.TeacherFeedbackSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.UserSupplier;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TeacherFeedbackServiceTest extends CrudServiceTest<TeacherFeedback, Integer> {
    private TeacherFeedbackService teacherFeedbackService;
    private TeacherFeedbackRepository teacherFeedbackRepository;
    private TeacherFeedbackSupplier teacherFeedbackSupplier;
    private UserRepository userRepository;
    private UserSupplier userSupplier;

    public TeacherFeedbackServiceTest() {
        teacherFeedbackRepository = Mockito.mock(TeacherFeedbackRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        teacherFeedbackService = new TeacherFeedbackServiceImpl(teacherFeedbackRepository, userRepository);
        teacherFeedbackSupplier = new TeacherFeedbackSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void findByExistingTeacherIdTest() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(userRepository.findOne(any())).thenReturn(teacher);
        when(teacherFeedbackRepository.findByTeacherIdOrderByIdDesc(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<TeacherFeedback>> result = teacherFeedbackService.findByTeacherId(teacher.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(teacherFeedbackRepository, times(1)).findByTeacherIdOrderByIdDesc(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingTeacherIdTest() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<TeacherFeedback>> result = teacherFeedbackService.findByTeacherId(student.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(teacherFeedbackRepository, times(0)).findByTeacherIdOrderByIdDesc(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByExistingStudentIdTest() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);
        when(teacherFeedbackRepository.findByStudentIdOrderByIdDesc(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<TeacherFeedback>> result = teacherFeedbackService.findByStudentId(student.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(teacherFeedbackRepository, times(1)).findByStudentIdOrderByIdDesc(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingStudentIdTest() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(userRepository.findOne(any())).thenReturn(teacher);

        Optional<List<TeacherFeedback>> result = teacherFeedbackService.findByStudentId(teacher.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(teacherFeedbackRepository, times(0)).findByStudentIdOrderByIdDesc(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Override
    protected CrudService<TeacherFeedback, Integer> getCrudService() {
        return teacherFeedbackService;
    }

    @Override
    protected CrudRepository<TeacherFeedback, Integer> getCrudRepository() {
        return teacherFeedbackRepository;
    }

    @Override
    protected EntitySupplier<TeacherFeedback, Integer> getEntitySupplier() {
        return teacherFeedbackSupplier;
    }
}
