package main.controller;

import main.model.ClassDirected;
import main.service.ClassDirectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassDirectedController {
    @Autowired
    private ClassDirectedService classDirectedService;

    @GetMapping("/gym/{id}")
    public List<ClassDirected> getAllByGym(@PathVariable Integer id) {
        return classDirectedService.getAllClassDirectedOfGym(id);
    }
}
