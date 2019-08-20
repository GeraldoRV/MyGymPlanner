package main.dao;

import main.model.WorkoutTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTableDao extends CrudRepository<WorkoutTable, Integer> {
}
