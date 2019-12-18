package main.controller;

import main.model.Team;
import main.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team")
@CrossOrigin(origins = "*")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.create(team);
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.getAll();
    }
}
