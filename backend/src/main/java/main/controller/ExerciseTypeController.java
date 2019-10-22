package main.controller;

import main.model.ExerciseType;
import main.service.ExerciseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("exerciseType")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseTypeController {
    @Autowired
    private ExerciseTypeService exerciseTypeService;

    @GetMapping
    public List<ExerciseType> getAllExerciseType() {
        return exerciseTypeService.getAll();
    }
}
