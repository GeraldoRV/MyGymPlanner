package main.controller;

import main.model.ExerciseType;
import main.service.ExerciseTypeService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("category/{name}")
    public List<ExerciseType> getAllExerciseTypeByCategoryName(@PathVariable String name) {
        return exerciseTypeService.getAllByCategoryName(name);
    }

}
