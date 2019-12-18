package main.service;

import main.dao.WorkoutTableDao;
import main.model.Exercise;
import main.model.WorkoutTable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutTableService {

    private final WorkoutTableDao wtDao;

    public WorkoutTableService(WorkoutTableDao wtDao) {
        this.wtDao = wtDao;
    }

    public Optional<WorkoutTable> getWTable(Integer id) {
        return wtDao.findById(id);
    }

    public List<WorkoutTable> getAllTablesByGym(Integer id) {
        return wtDao.findAllByUser_Gym_IdAndUser_Role(id, "admin");
    }

    public List<WorkoutTable> getAllTablesByUser(Integer id) {
        return wtDao.findAllByUser_Id(id);
    }

    public WorkoutTable setTableToClient(WorkoutTable workoutTable) {

        workoutTable.setId(null);
        workoutTable.setName(workoutTable.getName().concat("(copy)"));
        List<Exercise> exerciseList = workoutTable.getExerciseList();
        if (exerciseList != null) {
            changeIdsExercise(exerciseList);
        }

        return wtDao.save(workoutTable);
    }

    public WorkoutTable update(WorkoutTable workoutTable) {
        return wtDao.save(workoutTable);
    }

    public void delete(Integer workoutTableId) {
        wtDao.deleteById(workoutTableId);
    }

    private void changeIdsExercise(List<Exercise> exerciseList) {
        for (Exercise exercise :
                exerciseList) {
            exercise.setId(null);
        }
    }
}
