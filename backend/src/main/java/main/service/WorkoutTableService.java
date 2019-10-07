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

    public List<WorkoutTable> getAllTables() {
        return (List<WorkoutTable>) wtDao.findAll();
    }

    public Optional<WorkoutTable> get(Integer id) {
        return wtDao.findById(id);
    }
}
