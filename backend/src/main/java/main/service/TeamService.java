package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.exception.NotValidTeamException;
import main.exception.TeamNotFoundException;
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

    public Team create(Team team) throws NotValidTeamException {
        if (!isAValid(team)) {
            throw new NotValidTeamException();
        }

        User leader = team.getLeader();
        leader.setLeader(true);
        User save = userDao.save(leader);
        team.getMembers().add(save);
        return teamDAO.save(team);
    }

    private boolean existTeamByLeader(User leader) {
        return teamDAO.existsByLeader_Id(leader.getId());
    }

    public List<Team> getAll() {
        return (List<Team>) teamDAO.findAll();
    }

    public Team getTeamOfLeader(Integer leaderId) throws TeamNotFoundException {
        Team team = teamDAO.findByLeader_Id(leaderId);
        if (team == null) {
            throw new TeamNotFoundException();
        }
        return team;
    }

    private boolean isAValid(Team team) {
        boolean valid = true;
        if (team.getName() == null || team.getName().isEmpty()){
            return false;
        }
        if (team.getLeader() == null || existTeamByLeader(team.getLeader())){
            return false;
        }
        List<User> members = team.getMembers();
        for (User member :
                members) {
            if(existTeamByMember(member)) {
                return false;
            }
        }
        return valid;
    }

    private boolean existTeamByMember(User member) {
        return teamDAO.existsByMembersContains(member);
    }
}
