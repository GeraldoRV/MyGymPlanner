package main.service;

import main.dao.ExerciseTypeDao;
import main.model.ExerciseType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseTypeService {
    private final ExerciseTypeDao exerciseTypeDao;

    public ExerciseTypeService(ExerciseTypeDao exerciseTypeDao) {
        this.exerciseTypeDao = exerciseTypeDao;
    }

    public List<ExerciseType> getAll() {
        return (List<ExerciseType>) exerciseTypeDao.findAll();
    }

    public List<ExerciseType> getAllByCategoryName(String name) {
        return exerciseTypeDao.findAllByCategory_Name(name);
    }
}
