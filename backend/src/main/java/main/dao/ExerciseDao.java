package main.dao;

import main.model.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseDao extends CrudRepository<Exercise,Integer> {
}
