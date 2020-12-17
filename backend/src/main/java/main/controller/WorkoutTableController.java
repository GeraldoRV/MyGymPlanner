package main.controller;

import main.model.WorkoutTable;
import main.service.WorkoutTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "*")
public class WorkoutTableController {

    private final WorkoutTableService wtService;

    public WorkoutTableController(WorkoutTableService wtService) {
        this.wtService = wtService;
    }

    @GetMapping("/{id}")
    public Optional<WorkoutTable> getWorkTable(@PathVariable Integer id) {
        return wtService.getWTable(id);
    }

    @GetMapping("gym/{id}")
    public List<WorkoutTable> getAllByGym(@PathVariable Integer id) {
        return wtService.getAllTablesByGym(id);
    }

    @GetMapping("user/{id}")
    public List<WorkoutTable> getAllByUser(@PathVariable Integer id) {
        return wtService.getAllTablesByUser(id);
    }

    @PutMapping("client")
    public WorkoutTable setTableToClient(@RequestBody WorkoutTable workoutTable) {
        return wtService.setTableToClient(workoutTable);
    }

    @PutMapping
    public WorkoutTable updateWorkoutTable(@RequestBody WorkoutTable workoutTable) {
        return wtService.update(workoutTable);
    }

    @DeleteMapping("{id}")
    public void deleteWorkoutTable(@PathVariable Integer id) {
        wtService.delete(id);
    }
}
