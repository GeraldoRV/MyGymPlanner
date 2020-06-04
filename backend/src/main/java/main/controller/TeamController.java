package main.controller;

import main.converter.TeamConverter;
import main.dto.TeamDto;
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
    private final TeamConverter teamConverter;

    public TeamController(TeamService teamService, TeamConverter teamConverter) {
        this.teamService = teamService;
        this.teamConverter = teamConverter;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAll();
    }

    @GetMapping("leader/{id}")
    public TeamDto getTeamOfLeader(@PathVariable Integer id) {
        return teamConverter.transformToTeamDtoFromEntity(teamService.getTeamOfLeader(id));
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) throws NotValidTeamException {
        return teamService.create(team);
    }
}
