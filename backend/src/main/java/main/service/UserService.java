package main.service;

import main.dao.UserDao;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(User user) {
        return userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());
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

    public List<User> getAll() {
        return (List<User>) userDao.findAll();
    }

    public List<User> getAllClientsWhereNameLike(String name) {
        return userDao.findAllByNameContainingAndRole(name,"client");
    }
}
