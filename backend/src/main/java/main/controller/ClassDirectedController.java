package main.controller;

import main.model.ClassDirected;
import main.service.ClassDirectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "*")
public class ClassDirectedController {
    @Autowired
    private ClassDirectedService classDirectedService;

    @GetMapping("{id}")
    public Optional<ClassDirected> getClassDirected(@PathVariable Integer id){
        return classDirectedService.getClassDirected(id);
    }
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
    public boolean reserveClass(@RequestBody ClassDirected classDirected, @PathVariable Integer id) {
        return classDirectedService.reserveAClass(classDirected, id, null);
    }
    @PutMapping("add/client/{id}")
    public boolean addClientToClass(@RequestBody ClassDirected classDirected, @PathVariable Integer id){
        return classDirectedService.addClientToClass(classDirected,id);
    }
}
