package main.controller;

import main.exception.UserNotFoundException;
import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User login(@RequestBody User user) throws UserNotFoundException {
        return userService.login(user);
    }
}
