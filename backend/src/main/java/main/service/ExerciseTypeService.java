package main.service;

import main.dao.ExerciseTypeDao;
import main.model.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseTypeService {
    @Autowired
    private ExerciseTypeDao exerciseTypeDao;

    public List<ExerciseType> getAll() {
        return (List<ExerciseType>) exerciseTypeDao.findAll();
    }
}
