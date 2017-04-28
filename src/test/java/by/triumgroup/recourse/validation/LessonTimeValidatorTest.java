package by.triumgroup.recourse.validation;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import by.triumgroup.recourse.validation.validator.LessonTimeValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static by.triumgroup.recourse.util.TestUtil.createValidationErrors;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LessonTimeValidatorTest {
    @InjectMocks
    private LessonTimeValidator lessonTimeValidator;

    @Mock
    private LessonRepository lessonRepository;

    private LessonSupplier lessonSupplier;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        lessonSupplier = new LessonSupplier();
    }

    @Test
    public void updateLessonWithoutChangingTimeOrTeacher() throws Exception {
        Lesson lesson = lessonSupplier.getValidEntityWithId();
        when(lessonRepository.findOne(lesson.getId())).thenReturn(lesson);
        Errors errors = createValidationErrors(lesson, "lesson");

        lessonTimeValidator.validate(lesson, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(lessonRepository, times(1)).findOne(lesson.getId());
        verify(lessonRepository, times(0)).canUpdateLesson(any(), any(), any(), any());
    }

    @Test
    public void updateLessonToFreeTeacherTest() throws Exception {
        Integer id = lessonSupplier.getAnyId();
        Lesson newLesson = lessonSupplier.getValidEntityWithoutId();
        Lesson oldLesson = lessonSupplier.getValidEntityWithoutId();
        newLesson.setId(id);
        oldLesson.setId(id);
        oldLesson.getTeacher().setId(newLesson.getTeacher().getId() + 1);
        when(lessonRepository.findOne(newLesson.getId())).thenReturn(oldLesson);
        when(lessonRepository.canUpdateLesson(
                newLesson.getStartTime(),
                newLesson.getTeacher().getId(),
                newLesson.getDuration(),
                newLesson.getId())).thenReturn(true);
        Errors errors = createValidationErrors(newLesson, "lesson");

        lessonTimeValidator.validate(newLesson, errors);

        Assert.assertFalse(errors.hasFieldErrors());
        verify(lessonRepository, times(1)).findOne(id);
        verify(lessonRepository, times(1)).canUpdateLesson(
                newLesson.getStartTime(),
                newLesson.getTeacher().getId(),
                newLesson.getDuration(),
                newLesson.getId());
    }

    @Test
    public void updateLessonToBusyTeacherTest() throws Exception {
        Integer id = lessonSupplier.getAnyId();
        Lesson newLesson = lessonSupplier.getValidEntityWithoutId();
        Lesson oldLesson = lessonSupplier.getValidEntityWithoutId();
        newLesson.setId(id);
        oldLesson.setId(id);
        oldLesson.getTeacher().setId(newLesson.getTeacher().getId() + 1);
        when(lessonRepository.findOne(newLesson.getId())).thenReturn(oldLesson);
        when(lessonRepository.canUpdateLesson(
                newLesson.getStartTime(),
                newLesson.getTeacher().getId(),
                newLesson.getDuration(),
                newLesson.getId())).thenReturn(false);
        Errors errors = createValidationErrors(newLesson, "lesson");

        lessonTimeValidator.validate(newLesson, errors);

        Assert.assertTrue(errors.hasFieldErrors());
        Assert.assertTrue(errors.getErrorCount() == 1);
        FieldError fieldError = errors.getFieldError();
        Assert.assertEquals("teacher", fieldError.getField());
        verify(lessonRepository, Mockito.times(1)).findOne(id);
        verify(lessonRepository, times(1)).canUpdateLesson(
                newLesson.getStartTime(),
                newLesson.getTeacher().getId(),
                newLesson.getDuration(),
                newLesson.getId());
    }
}
