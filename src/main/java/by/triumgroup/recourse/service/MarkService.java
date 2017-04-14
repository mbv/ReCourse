package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.Mark;

import java.util.Optional;

public interface MarkService extends CrudService<Mark, Integer> {

    Optional<Mark> findBySolutionId(Integer id);

}
