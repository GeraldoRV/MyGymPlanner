package main.service;

import main.dao.ExerciseDao;
import main.dao.WorkoutTableDao;
import main.model.Exercise;
import main.model.WorkoutTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutTableService {

    @Autowired
    private WorkoutTableDao wtDao;
    @Autowired
    private ExerciseDao exerciseDao;

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
        if (exerciseList != null && !exerciseList.isEmpty()) {
            List<Exercise> exercises = new ArrayList<>();
            for (Exercise exercise :
                    exerciseList) {
                exercise.setId(null);
                exercises.add(exercise);
            }
            List<Exercise> list = (List<Exercise>) exerciseDao.saveAll(exercises);
            workoutTable.setExerciseList(list);

        }
            return wtDao.save(workoutTable);
    }
}
