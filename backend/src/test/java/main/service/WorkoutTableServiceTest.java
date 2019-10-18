package main.service;

import main.dao.GymDao;
import main.dao.UserDao;
import main.dao.WorkoutTableDao;
import main.model.Gym;
import main.model.User;
import main.model.WorkoutTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WorkoutTableServiceTest {
    @Autowired
    private WorkoutTableDao wtDao;
    @Autowired
    private GymDao gymDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkoutTableService wtService;
    private Gym gymWithWK;
    private List<WorkoutTable> workoutTableOfGym;
    private User admin;
    private User client;

    @Before
    public void setUp() throws Exception {
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setName("WKT");
        WorkoutTable workoutTableUser = new WorkoutTable();
        workoutTableUser.setName("WKT");
        Gym gym = new Gym();
        gym.setName("G");
        Gym save = gymDao.save(gym);
        gymWithWK = save;

        User admin = new User();
        admin.setGym(save);
        admin.setRole("admin");

        User client = new User();
        client.setGym(save);
        client.setRole("client");

        this.admin = userDao.save(admin);
         this.client = userDao.save(client);
        workoutTable.setUser(this.admin);
        workoutTableUser.setUser(this.client);
        workoutTableOfGym= (List<WorkoutTable>) wtDao.saveAll(Arrays.asList(workoutTableUser, workoutTable));
    }


    @Test
    public void givenAExistGymWithWT_whenGetAllTablesByGym_returnAllWorkoutTablesOfTheGym() {
        List<WorkoutTable> allTablesByGym = wtService.getAllTablesByGym(gymWithWK.getId());

        assertEquals("Algo ha salido mal", 1, allTablesByGym.size());
    }

    @Test
    public void givenAExistGymWithNotWK_whenGetAllTablesByGym_returnAEmptyList() {
        Gym gym = new Gym();
        gym.setName("G3");
        Gym newGym = gymDao.save(gym);


        List<WorkoutTable> allTablesByGym = wtService.getAllTablesByGym(newGym.getId());
        System.out.println("The size: " + allTablesByGym.size());
        assertEquals("Algo ha salido mal", 0, allTablesByGym.size());
    }

    @Test
    public void givenANotExistGym_whenGetAllTablesByGym_returnAEmptyList() {
        Gym gym = new Gym();
        gym.setId(300);
        gym.setName("G2");
        List<WorkoutTable> allTablesByGym = wtService.getAllTablesByGym(gym.getId());
        assertEquals("Algo ha salido mal", 0, allTablesByGym.size());
    }

    @Test
    public void givenAExistWT_whenGet_returnAWorkoutTable() {
        WorkoutTable workoutTable = workoutTableOfGym.get(0);
        Optional<WorkoutTable> workoutTableDB = wtService.get(workoutTable.getId());
        assertTrue("Algo ha salido mal",workoutTableDB.isPresent());
    }

    @Test
    public void givenANotExistWT_whenGet_returnNull() {
        Optional<WorkoutTable> workoutTableDB = wtService.get(255);
        assertFalse("Algo ha salido mal",workoutTableDB.isPresent());
    }

    @Test
    public void givenAWTOfTheGymModifiedForClient_whenModifyToClient_returnAModifyWtk() {
        WorkoutTable workoutTableModified = new WorkoutTable();
        for (WorkoutTable workoutTable: workoutTableOfGym
             ) {
            if(workoutTable.getUser().getRole().equals("admin")){
                workoutTableModified= workoutTable;
                break;
            }
        }

        workoutTableModified.setUser(client);
        WorkoutTable workoutTable = wtService.modifyToClient(workoutTableModified);
        assertEquals("Algo Mal",workoutTableModified.getUser().getRole(),workoutTable.getUser().getRole());
    }

    @Test
    public void givenAWTOfTheClientModifiedForClient_whenModifyToClient_returnAModifyNull() {
        WorkoutTable workoutTableModified = new WorkoutTable();
        for (WorkoutTable workoutTable: workoutTableOfGym
        ) {
            if(workoutTable.getUser().getRole().equals("client")){
                workoutTableModified= workoutTable;
                break;
            }
        }

        workoutTableModified.setUser(client);
        WorkoutTable workoutTable = wtService.modifyToClient(workoutTableModified);
        assertNull("Algo Mal",workoutTable);
    }

    @Test
    public void givenAExistWTModified_whenUpdate_ReturnWtModified() {
        WorkoutTable workoutTable = workoutTableOfGym.get(0);
        workoutTable.setLevel('C');
        workoutTable.setExerciseList(new ArrayList<>());
        WorkoutTable update = wtService.update(workoutTable);
        assertEquals("Algo ha ido mal", new Character('C'), update.getLevel());
    }
}
