package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.model.Team;
import main.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamDAO teamDAO;
    private UserDao userDao;

    public TeamService(TeamDAO teamDAO, UserDao userDao) {
        this.teamDAO = teamDAO;
        this.userDao = userDao;
    }

    public Team create(Team team) {
        User leader = team.getLeader();
        leader.setLeader(true);
        User save = userDao.save(leader);
        team.getMembers().add(save);
        return teamDAO.save(team);
    }

    public List<Team> getAll() {
        return (List<Team>) teamDAO.findAll();
    }

    public Team getTeamOfLeader(Integer leaderId) {
        return teamDAO.findByLeader_Id(leaderId);
    }
}
