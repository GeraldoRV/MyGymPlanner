package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.exception.TeamNotFoundException;
import main.exception.UserNotFoundException;
import main.model.Team;
import main.model.User;
import main.model.WorkingHours;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserDao userDao;
    private final TeamDAO teamDAO;

    public UserService(UserDao userDao, TeamDAO teamDAO) {
        this.userDao = userDao;
        this.teamDAO = teamDAO;
    }

    public List<User> getAll() {
        return (List<User>) userDao.findAll();
    }

    public List<User> getAllClientsWhereNameLike(String name) {
        return userDao.findAllByNameContainingAndRole(name, "client");
    }

    public List<User> getAllMonitorsNotInATeam() {
        return userDao.findAllMonitorsNotInATeam();
    }

    public User login(User user) throws UserNotFoundException {
        User userLogged = userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userLogged == null) throw new UserNotFoundException();
        return userLogged;
    }

    public User create(User user) {
        if (isValidUser(user)) {
            if (user.getRole().equals("monitor")) {
                user.setWorkingHours(createRandomWorkingHours());
            }
            setDefaultPassword(user);
            return userDao.save(user);
        }

        return null;
    }

    private boolean isValidUser(User user) {
        return user.getName() != null && !user.getName().isEmpty() &&
                user.getUserName() != null && !user.getUserName().isEmpty() &&
                user.getRole() != null && !user.getRole().isEmpty() && !isNotARoleValid(user.getRole());
    }

    private void setDefaultPassword(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("1234");
        }
    }

    private boolean isNotARoleValid(String role) {
        return !role.equals("admin") && !role.equals("client") && !role.equals("monitor");
    }

    private WorkingHours createRandomWorkingHours() {
        WorkingHours workingHours = new WorkingHours();
        Random random = new Random();
        int randomN = random.nextInt(4);

        switch (randomN) {
            case 1:
                setWorkingHours(workingHours, "15:00 to 23:00", "15:00 to 21:00", "Free");
                break;
            case 2:
                setWorkingHours(workingHours, "08:00 to 15:00", "Free", "08:00 to 15:00");
                break;
            case 3:
                setWorkingHours(workingHours, "08:00 to 15:00", "09:00 to 15:00", "Free");
                break;
            default:
                setWorkingHours(workingHours, "15:00 to 23:00", "Free", "08:00 to 15:00");
        }
        return workingHours;
    }

    private void setWorkingHours(WorkingHours workingHours, String mondayToFriday, String saturdays, String sundays) {
        workingHours.setMondayToFriday(mondayToFriday);
        workingHours.setSaturday(saturdays);
        workingHours.setSunday(sundays);

    }

    public List<User> getAllMonitorOfTeamLeader(Integer leaderId) {
        Team team = teamDAO.findByLeader_Id(leaderId);
        return team.getMembers();

    }
}
