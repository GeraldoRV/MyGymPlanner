package main.controller;

import main.model.ClassDirected;
import main.service.ClassDirectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassDirectedController {
    @Autowired
    private ClassDirectedService classDirectedService;

    @GetMapping("/gym/{id}")
    public List<ClassDirected> getAllByGym(@PathVariable Integer id) {
        return classDirectedService.getAllClassDirectedOfGym(id);
    }

    @GetMapping("gym/{id}/{dayOfWeek}")
    public List<ClassDirected> getAllByGymAndDay(@PathVariable Integer id, @PathVariable String dayOfWeek) {
        return classDirectedService.getAllClassDirectedOfDayAndGym(dayOfWeek, id);
    }

    @GetMapping("monitor/{id}/{dayOfWeek}")
    public List<ClassDirected> getAllByMonitorAndDay(@PathVariable Integer id, @PathVariable String dayOfWeek) {
        return classDirectedService.getAllClassDirectedOfMonitorAndDay(id, dayOfWeek);
    }

    @PutMapping("client/{id}")
    public boolean addClientToClass(@RequestBody ClassDirected classDirected, @PathVariable Integer id) {
        return classDirectedService.addClientInAClass(classDirected, id);
    }
}
