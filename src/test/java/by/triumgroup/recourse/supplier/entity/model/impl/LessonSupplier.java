package by.triumgroup.recourse.supplier.entity.model.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.sql.Time;
import java.sql.Timestamp;

public class LessonSupplier implements EntityIntegerPKSupplier<Lesson> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public Lesson getValidEntityWithoutId() {
        Lesson lesson = new Lesson();
        lesson.setCourseId(1);
        lesson.setDuration(Time.valueOf("1:00:00"));
        lesson.setStartTime(new Timestamp(Long.MAX_VALUE));
        lesson.setTeacher(userSupplier.getValidEntityWithId());
        lesson.setTopic("topic");
        return lesson;
    }

    @Override
    public Lesson getInvalidEntity() {
        return new Lesson();
    }
}
