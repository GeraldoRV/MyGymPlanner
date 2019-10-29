package main.service;

import main.dao.WorkoutTableDao;
import main.model.Exercise;
import main.model.WorkoutTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutTableService {

    @Autowired
    private WorkoutTableDao wtDao;

    public List<WorkoutTable> getAllTablesByGym(Integer id) {
        return wtDao.findAllByUser_Gym_IdAndUser_Role(id, "admin");
    }

    public Optional<WorkoutTable> get(Integer id) {
        return wtDao.findById(id);
    }

    public WorkoutTable modifyToClient(WorkoutTable workoutTable) {
    
        workoutTable.setId(null);
        workoutTable.setName(workoutTable.getName().concat("(copy)"));
        List<Exercise> exerciseList = workoutTable.getExerciseList();
        if (exerciseList != null){
            changeIdsExercise(exerciseList);
        }

        return wtDao.save(workoutTable);
    }

    private void changeIdsExercise(List<Exercise> exerciseList) {
        for (Exercise exercise :
                exerciseList) {
            exercise.setId(null);
        }
    }

    public WorkoutTable update(WorkoutTable workoutTable) {
        return wtDao.save(workoutTable);
    }

    public List<WorkoutTable> getAllTablesByUser(Integer id) {
        return wtDao.findAllByUser_Id(id);
    }
}
