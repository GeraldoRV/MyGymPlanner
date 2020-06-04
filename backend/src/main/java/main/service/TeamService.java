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
    private final UserDao userDao;

    public TeamService(TeamDAO teamDAO, UserDao userDao) {
        this.teamDAO = teamDAO;
        this.userDao = userDao;
    }

    public List<Team> getAll() {
        return (List<Team>) teamDAO.findAll();
    }

    public Team getTeamOfLeader(Integer leaderId) {
        return teamDAO.findByLeader_Id(leaderId)
                .orElseThrow(TeamNotFoundException::new);
    }

    public Team create(Team team) throws NotValidTeamException {
        if (!isAValid(team)) {
            throw new NotValidTeamException();
        }

        User leader = team.getLeader();
        leader.setLeader(true);
        userDao.save(leader);
        if (!leaderIsAMember(team.getMembers(), leader)) {
            team.getMembers().add(leader);
        }

        return teamDAO.save(team);
    }

    private boolean leaderIsAMember(List<User> members, User leader) {
        for (User member :
                members) {
            if (member.getId().equals(leader.getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean existTeamByLeader(User leader) {
        return teamDAO.existsByLeader_Id(leader.getId());
    }

    private boolean isAValid(Team team) {

        if (team.getName() == null || team.getName().isEmpty()) {
            return false;
        }
        if (team.getLeader() == null || existTeamByLeader(team.getLeader())) {
            return false;
        }
        List<User> members = team.getMembers();
        for (User member :
                members) {
            if (existTeamByMember(member)) {
                return false;
            }
        }
        return true;
    }

    private boolean existTeamByMember(User member) {
        return teamDAO.existsByMembersContains(member);
    }
}
