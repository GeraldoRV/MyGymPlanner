package main.controller;

import main.model.WorkoutTable;
import main.service.WorkoutTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/table")
public class WorkoutTableController {
    @Autowired
    private WorkoutTableService wtService;

    @GetMapping
    public List<WorkoutTable> findAll() {
        return wtService.getAllTables();
    }
}
