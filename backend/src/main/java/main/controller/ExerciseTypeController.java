package main.controller;

import main.model.ExerciseType;
import main.service.ExerciseTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("exerciseType")
@CrossOrigin(origins = "*")
public class ExerciseTypeController {
    private final ExerciseTypeService exerciseTypeService;

    public ExerciseTypeController(ExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

    @GetMapping
    public List<ExerciseType> getAllExerciseType() {
        return exerciseTypeService.getAll();
    }
}
