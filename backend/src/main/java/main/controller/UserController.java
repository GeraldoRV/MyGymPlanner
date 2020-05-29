package main.controller;

import main.converter.UserConverter;
import main.dto.UserTypeMonitorDto;
import main.model.User;
import main.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("client/{name}")
    public List<User> getClientsLikeTheName(@PathVariable String name) {
        return userService.getAllClientsWhereNameLike(name);
    }

    @GetMapping("monitor/team/{id}")
    public List<UserTypeMonitorDto> getAllMonitorsOfTeam(@PathVariable Integer id) {
        return userConverter.transformToMonitorTypeFromEntity(userService.getAllMonitorOfTeam(id));
    }


    @GetMapping("monitor/not-members")
    public List<User> getAllMonitorNotInTeam() {
        return userService.getAllMonitorsNotInATeam();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

}
