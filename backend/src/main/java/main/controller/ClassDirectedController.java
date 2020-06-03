package main.controller;

import main.converter.ClassDirectedConverter;
import main.dto.ClassDirectedDtoToAssign;
import main.dto.UserTypeMonitorDto;
import main.exception.ClassDirectedFullException;
import main.exception.NotValidDayToReserveException;
import main.exception.TheClientIsInTheClassException;
import main.model.ClassDirected;
import main.model.User;
import main.service.ClassDirectedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class")
@CrossOrigin(origins = "*")
public class ClassDirectedController {
    private final ClassDirectedService classDirectedService;
    private final ClassDirectedConverter classDirectedConverter;

    public ClassDirectedController(ClassDirectedService classDirectedService, ClassDirectedConverter classDirectedConverter) {
        this.classDirectedService = classDirectedService;
        this.classDirectedConverter = classDirectedConverter;
    }

    @GetMapping("{id}")
    public Optional<ClassDirected> getClassDirected(@PathVariable Integer id) {
        return classDirectedService.getClassDirected(id);
    }

    @GetMapping("/gym/{id}")
    public List<ClassDirected> getAllByGym(@PathVariable Integer id) {
        return classDirectedService.getAllClassDirectedOfGym(id);
    }

    @GetMapping("/type-class/{id}")
    public List<ClassDirectedDtoToAssign> getAllByTypeClass(@PathVariable Integer id) {
        return classDirectedConverter.transformToClassDtoToAssignFromEntity(classDirectedService.getAllClassDirectedOfTypeClass(id));
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
    public boolean reserveClass(@RequestBody ClassDirected classDirected, @PathVariable Integer id)
            throws NotValidDayToReserveException, TheClientIsInTheClassException,
            ClassDirectedFullException {
        return classDirectedService.reserveAClass(classDirected, id, null);
    }

    @PutMapping("add/client/{id}")
    public boolean addClientToClass(@RequestBody ClassDirected classDirected, @PathVariable Integer id) throws TheClientIsInTheClassException {
        return classDirectedService.addClientToClass(classDirected, id);
    }

    @PutMapping("{id}/monitor")
    public ClassDirected assignMonitor(@RequestBody UserTypeMonitorDto monitor, @PathVariable Integer id) {
        return classDirectedService.assignMonitor(monitor, id);
    }
}
