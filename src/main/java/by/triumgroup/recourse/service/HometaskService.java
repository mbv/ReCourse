package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Hometask;

import java.util.Optional;

public interface HometaskService extends CrudService<Hometask, Integer> {

    Optional<Hometask> findByLessonId(Integer id);

}
