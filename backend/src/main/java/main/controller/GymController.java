package main.controller;

import main.model.Gym;
import main.service.GymService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gym")
@CrossOrigin(origins = "*")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/{id}")
    public Optional<Gym> getGym(@PathVariable Integer id) {
        return gymService.getGym(id);
    }
}
