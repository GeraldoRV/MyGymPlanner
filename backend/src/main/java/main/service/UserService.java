package main.service;

import main.dao.UserDao;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User login(User user) {
        return userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());

    }
}
