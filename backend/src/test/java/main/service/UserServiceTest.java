package main.service;

import main.dao.UserDao;
import main.model.Gym;
import main.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setUserName("juan");
        user.setPassword("juan");
        userDao.save(user);

    }

    @After
    public void tearDown() throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void givenANewUserNotValid_whenCreateUser_returnNull() {
        User user = new User();
        user.setUserName("marco");
        user.setPassword("marco");

        User newUser = userService.createUser(user);

        assertNull(newUser);
    }

    @Test
    public void givenANewUserValid_whenCreateUser_returnNull() {
        User user = new User();
        user.setUserName("marco");
        user.setPassword("marco");
        user.setName("Marco");
        user.setRole("admin");

        User newUser = userService.createUser(user);

        assertNotNull(newUser);
    }

    @Test
    public void givenUserRegister_whenLogin_returnUser() {
        User user = new User();
        user.setUserName("juan");
        user.setPassword("juan");


        User login = userService.login(user);

        assertNotNull(login);
    }

    @Test
    public void givenNotUserRegister_whenLogin_returnNull() {
        User user = new User();
        user.setUserName("marco");
        user.setPassword("juan");

        User login = userService.login(user);

        assertNull(login);
    }

    @Test
    public void givenADBWithUsers_whenGetAll_returnAListUserInDB() {
        List<User> all = userService.getAll();
        assertEquals("Hubo un fallo",1, all.size());
    }
    
    @Test
    public void givenADBWithNotUsers_whenGetAll_returnAListUserInDB() {
        List<User> all = userService.getAll();
        userDao.deleteAll();
        List<User> none = userService.getAll();

        assertEquals("Hubo un fallo",0, none.size());

        userDao.saveAll(all);
    }

}
