package main.dao;

import main.model.ExerciseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTypeDao extends CrudRepository<ExerciseType,Integer> {
}
