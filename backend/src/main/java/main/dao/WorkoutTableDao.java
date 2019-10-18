package main.dao;

import main.model.WorkoutTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutTableDao extends CrudRepository<WorkoutTable, Integer> {
    List<WorkoutTable> findAllByUser_Gym_IdAndUser_Role(Integer id, String role);

    boolean existsByIdAndUser_Role(Integer id, String role);
}
