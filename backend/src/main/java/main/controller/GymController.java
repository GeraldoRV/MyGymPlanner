package main.controller;

import main.model.Gym;
import main.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gym")
@CrossOrigin(origins = "http://localhost:4200")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping("/{id}")
    public Optional<Gym> getGym(@PathVariable Integer id) {
        return gymService.getGym(id);
    }
}
