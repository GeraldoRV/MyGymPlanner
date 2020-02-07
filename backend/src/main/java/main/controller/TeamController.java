package main.controller;

import main.exception.NotValidTeamException;
import main.exception.TeamNotFoundException;
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

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAll();
    }

    @GetMapping("leader/{id}")
    public Team getTeamOfLeader(@PathVariable Integer id) throws TeamNotFoundException {
        return teamService.getTeamOfLeader(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) throws NotValidTeamException {
        return teamService.create(team);
    }
}
