package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("client/{name}")
    public List<User> getClientsLikeTheName(@PathVariable String name) {
        return userService.getAllClientsWhereNameLike(name);
    }

    @GetMapping("monitor/not-leaders")
    public List<User> getAllMonitorsNotLeaders() {
        return userService.getAllMonitorsNotLeaders();
    }

    @GetMapping("monitor/not-members")
    public List<User> getAllMonitorNotInTeam() {
        return userService.getAllMonitorsNotInATeam();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
