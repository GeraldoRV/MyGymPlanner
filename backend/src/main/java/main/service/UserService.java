package main.service;

import main.dao.UserDao;
import main.exception.UserNotFoundException;
import main.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> getAllMonitorsNotLeaders() {
        return userDao.findAllMonitorNotLeaders();
    }

    public User login(User user) throws UserNotFoundException {
        User userLogged = userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (userLogged == null) throw new UserNotFoundException();
        return userLogged;
    }

    public User createUser(User user) {
        if (isValidUser(user)) {
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

    public List<User> getAllMonitorsNotInATeam() {
        return userDao.findAllMonitorsNotInATeam();
    }
}
