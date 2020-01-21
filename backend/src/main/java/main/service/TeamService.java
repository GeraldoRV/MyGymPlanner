package main.service;

import main.dao.TeamDAO;
import main.model.Team;
import main.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public Team create(Team team) {
        User leader = team.getLeader();
        team.getMembers().add(leader);
        return teamDAO.save(team);
    }

    public List<Team> getAll() {
        return (List<Team>) teamDAO.findAll();
    }

    public Team getTeamOfLeader(Integer leaderId) {
        return teamDAO.findByLeader_Id(leaderId);
    }
}
