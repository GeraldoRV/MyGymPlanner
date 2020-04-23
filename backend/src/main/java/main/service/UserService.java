package main.service;

import main.dao.UserDao;
import main.exception.UserNotFoundException;
import main.model.User;
import main.model.WorkingHours;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
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
            if (user.getPassword() == null || user.getPassword().isEmpty()){
                user.setPassword("1234");
            }
            return userDao.save(user);
        }

        return null;
    }

    private boolean isValidUser(User user) {
        return user.getName() != null && !user.getName().isEmpty() &&
                user.getUserName() != null && !user.getUserName().isEmpty() &&
                user.getRole() != null && !user.getRole().isEmpty() && !isNotARoleValid(user.getRole());
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
                workingHours.setMondayToFriday("15:00 to 23:00");
                workingHours.setSaturday("15:00 to 21:00");
                workingHours.setSunday("Free");
                break;
            case 2:
                workingHours.setMondayToFriday("08:00 to 15:00");
                workingHours.setSaturday("Free");
                workingHours.setSunday("08:00 to 15:00");
                break;
            case 3:
                workingHours.setMondayToFriday("08:00 to 15:00");
                workingHours.setSaturday("09:00 to 15:00");
                workingHours.setSunday("Free");
                break;
            default:
                workingHours.setMondayToFriday("15:00 to 23:00");
                workingHours.setSaturday("Free");
                workingHours.setSunday("08:00 to 15:00");
        }
        return workingHours;
    }
}
