package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.StudentReportRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.StudentReportSupplier;
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

public class StudentReportServiceTest extends CrudServiceTest<StudentReport, Integer> {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private StudentReportService studentReportService;
    private StudentReportRepository studentReportRepository;
    private StudentReportSupplier studentReportSupplier;
    private UserSupplier userSupplier;

    public StudentReportServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        studentReportRepository = Mockito.mock(StudentReportRepository.class);
        studentReportService = new StudentReportServiceImpl(studentReportRepository, courseRepository, userRepository);
        studentReportSupplier = new StudentReportSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void findByExistingTeacherIdTest() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(userRepository.findOne(any())).thenReturn(teacher);
        when(studentReportRepository.findByTeacherId(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<StudentReport>> result = studentReportService.findByTeacherId(teacher.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(studentReportRepository, times(1)).findByTeacherId(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingTeacherIdTest() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);

        Optional<List<StudentReport>> result = studentReportService.findByTeacherId(student.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(studentReportRepository, times(0)).findByTeacherId(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByExistingCourseIdTest() throws Exception {
        when(courseRepository.exists(any())).thenReturn(true);
        when(studentReportRepository.findByTeacherId(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<StudentReport>> result = studentReportService.findByCourseId(userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(studentReportRepository, times(1)).findByCourseId(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingCourseIdTest() throws Exception {
        when(courseRepository.exists(any())).thenReturn(false);

        Optional<List<StudentReport>> result = studentReportService.findByCourseId(userSupplier.getAnyId(), null);

        verify(courseRepository, times(1)).exists(any());
        verify(studentReportRepository, times(0)).findByCourseId(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findByExistingStudentIdTest() throws Exception {
        User student = userSupplier.getValidEntityWithId();
        student.setRole(User.Role.STUDENT);
        when(userRepository.findOne(any())).thenReturn(student);
        when(studentReportRepository.findByStudentId(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<StudentReport>> result = studentReportService.findByStudentId(student.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(studentReportRepository, times(1)).findByStudentId(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingStudentIdTest() throws Exception {
        User teacher = userSupplier.getValidEntityWithId();
        teacher.setRole(User.Role.TEACHER);
        when(userRepository.findOne(any())).thenReturn(teacher);

        Optional<List<StudentReport>> result = studentReportService.findByStudentId(teacher.getId(), null);

        verify(userRepository, times(1)).findOne(any());
        verify(studentReportRepository, times(0)).findByStudentId(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Override
    protected CrudService<StudentReport, Integer> getCrudService() {
        return studentReportService;
    }

    @Override
    protected CrudRepository<StudentReport, Integer> getCrudRepository() {
        return studentReportRepository;
    }

    @Override
    protected EntitySupplier<StudentReport, Integer> getEntitySupplier() {
        return studentReportSupplier;
    }
}
