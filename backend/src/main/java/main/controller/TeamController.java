package main.controller;

import main.model.Team;
import main.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team")
@CrossOrigin(origins = "*")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.create(team);
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.getAll();
    }
}
