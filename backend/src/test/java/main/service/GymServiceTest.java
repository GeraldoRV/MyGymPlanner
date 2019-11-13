package main.service;

import main.dao.GymDao;
import main.model.Gym;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GymServiceTest {
    @Autowired
    GymDao gymDao;
    @Autowired
    GymService gymService;


    @Test
    public void givenExistGym_WhenGetGym_thenReturnGym() {
        Gym gym = new Gym();
        gym.setName("gym");
        gym.setId(1);
        gymDao.save(gym);

        Optional<Gym> gym1 = gymService.getGym(1);

        assertTrue(gym1.isPresent());
    }

    @Test
    public void givenNotExistGym_WhenGetGym_thenReturnGym() {
        Optional<Gym> gym = gymService.getGym(200000);
        assertFalse(gym.isPresent());
    }
}
