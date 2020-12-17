package main.dao;

import main.model.ExerciseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseTypeDao extends CrudRepository<ExerciseType,Integer> {
    List<ExerciseType> findAllByCategory_Name(String name);
}
