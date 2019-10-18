package main.service;

import main.dao.WorkoutTableDao;
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
        if(!isOfTheGym(workoutTable)){
            return null;
        }
        workoutTable.setId(null);
        workoutTable.setName(workoutTable.getName().concat("(copy)"));

        return wtDao.save(workoutTable);
    }

    private boolean isOfTheGym(WorkoutTable workoutTable) {
        return wtDao.existsByIdAndUser_Role(workoutTable.getId(), "admin");
    }

    public WorkoutTable update(WorkoutTable workoutTable) {
        return wtDao.save(workoutTable);
    }
}
