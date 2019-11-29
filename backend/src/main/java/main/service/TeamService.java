package main.service;

import main.dao.TeamDAO;
import main.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamDAO teamDAO;

    public Team create(Team team) {
        return teamDAO.save(team);
    }

    public List<Team> getAll() {
        return (List<Team>) teamDAO.findAll();
    }
}
