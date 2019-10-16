package main.controller;

import main.model.WorkoutTable;
import main.service.WorkoutTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkoutTableController {

    @Autowired
    private WorkoutTableService wtService;

    @GetMapping("gym/{id}")
    public List<WorkoutTable> findAllByGym(@PathVariable Integer id) {
        return wtService.getAllTablesByGym(id);
    }

    @GetMapping("/{id}")
    public Optional<WorkoutTable> getWorkTable(@PathVariable Integer id) {
        return wtService.get(id);
    }

    @PutMapping("client")
    public WorkoutTable modifyWorkoutTableClient(@RequestBody WorkoutTable workoutTable){
        return wtService.modifyToClient(workoutTable);
    }
}
