package main.service;

import main.dao.GymDao;
import main.dao.UserDao;
import main.dao.WorkoutTableDao;
import main.model.Exercise;
import main.model.Gym;
import main.model.User;
import main.model.WorkoutTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private User admin;
    private User client;
    private WorkoutTable workoutTableAdmin;
    private WorkoutTable workoutTableClient;
    private WorkoutTable workoutTableNEAdmin;
    private WorkoutTable workoutTable2EAdmin;

    @Before
    public void setUp() throws Exception {
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setName("WKT");
        WorkoutTable workoutTableNotExercises = new WorkoutTable();
        workoutTableNotExercises.setName("WKTNE");
        WorkoutTable workoutTableTwoExercises = new WorkoutTable();
        workoutTableTwoExercises.setName("WKT2");
        WorkoutTable workoutTableUser = new WorkoutTable();
        workoutTableUser.setName("WKT");
        List<Exercise> exercises = new ArrayList<>();
        List<Exercise> exercises2 = new ArrayList<>();
        Exercise exercise = new Exercise();
        Exercise exercise2 = new Exercise();
        Exercise exercise3 = new Exercise();
        Exercise exercise4 = new Exercise();
        exercises.add(exercise);
        exercises2.add(exercise2);
        List<Exercise> exercisesTwoExercise = new ArrayList<>(Arrays.asList(exercise3, exercise4));
        workoutTableUser.setExerciseList(exercises);
        workoutTable.setExerciseList(exercises2);
        workoutTableTwoExercises.setExerciseList(exercisesTwoExercise);

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
        workoutTableNotExercises.setUser(this.admin);
        workoutTableTwoExercises.setUser(this.admin);
        workoutTableUser.setUser(this.client);
        workoutTableAdmin = wtDao.save(workoutTable);
        workoutTableClient = wtDao.save(workoutTableUser);
        workoutTableNEAdmin = wtDao.save(workoutTableNotExercises);
        workoutTable2EAdmin = wtDao.save(workoutTableTwoExercises);
    }


    @Test
    public void givenAExistGymWithWT_whenGetAllTablesByGym_returnAllWorkoutTablesOfTheGym() {
        List<WorkoutTable> allTablesByGym = wtService.getAllTablesByGym(gymWithWK.getId());

        assertEquals("Algo ha salido mal", 3, allTablesByGym.size());
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
        Optional<WorkoutTable> workoutTableDB = wtService.get(workoutTableAdmin.getId());
        assertTrue("Algo ha salido mal", workoutTableDB.isPresent());
    }

    @Test
    public void givenANotExistWT_whenGet_returnNull() {
        Optional<WorkoutTable> workoutTableDB = wtService.get(255);
        assertFalse("Algo ha salido mal", workoutTableDB.isPresent());
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRED)
    public void givenAWTWithExercisesOfTheGymModifiedForClient_whenModifyToClient_returnAModifyWtk() {
        Optional<WorkoutTable> byId = wtDao.findById(workoutTableAdmin.getId());
        if (!byId.isPresent()) return;
        WorkoutTable workoutTable1 = byId.get();
        workoutTable1.setUser(client);
        WorkoutTable workoutTable = wtService.createACopyToClient(workoutTable1);
        assertEquals("Algo Mal", workoutTable1.getUser().getRole(), workoutTable.getUser().getRole());
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRED)
    public void givenAWTWithNotExercisesOfTheGymModifiedForClient_whenModifyToClient_returnAModifyWtk() {
        Optional<WorkoutTable> byId = wtDao.findById(workoutTableNEAdmin.getId());
        if (!byId.isPresent()) return;
        WorkoutTable workoutTable1 = byId.get();
        workoutTable1.setUser(client);
        WorkoutTable workoutTable = wtService.createACopyToClient(workoutTable1);
        assertEquals("Algo Mal", workoutTable1.getUser().getRole(), workoutTable.getUser().getRole());
    }

    @Test
    public void givenAExistWTWithNotExerciseModified_whenUpdate_ReturnWtModified() {

        workoutTableNEAdmin.setLevel('C');

        WorkoutTable update = wtService.update(workoutTableNEAdmin);
        assertEquals("Algo ha ido mal", new Character('C'), update.getLevel());
    }

    @Test
    public void givenAExistWTWithNotExerciseAddNewExercise_whenUpdate_ReturnWtModified() {
        List<Exercise> exerciseList = new ArrayList<>();

        exerciseList.add(new Exercise());
        workoutTableNEAdmin.setExerciseList(exerciseList);

        WorkoutTable update = wtService.update(workoutTableNEAdmin);

        assertEquals("Algo ha ido mal", 1, update.getExerciseList().size());
    }

    @Test
    public void givenAExistWTWithExercisesAddNewExercise_whenUpdate_ReturnWtModified() {
        List<Exercise> exerciseList = workoutTableAdmin.getExerciseList();

        exerciseList.add(new Exercise());


        WorkoutTable update = wtService.update(workoutTableAdmin);

        assertEquals("Algo ha ido mal", 2, update.getExerciseList().size());
    }

    @Test
    public void givenAExistWTWithExercisesExerciseModified_whenUpdate_ReturnWtModified() {

        List<Exercise> exerciseList = workoutTableAdmin.getExerciseList();

        Exercise exercise = exerciseList.get(0);
        exercise.setSets(3);

        WorkoutTable update = wtService.update(workoutTableAdmin);
        Exercise exerciseUp = update.getExerciseList().get(0);
        Integer integer = 3;
        assertEquals(integer, exerciseUp.getSets());
    }

    @Test
    public void givenAExistWTWithExercisesRemoveAExercise_whenUpdate_ReturnWtModified() {
        List<Exercise> exerciseList = workoutTable2EAdmin.getExerciseList();

        exerciseList.remove(0);

        WorkoutTable update = wtService.update(workoutTable2EAdmin);

        assertEquals("Algo ha ido mal", 1, update.getExerciseList().size());
    }

    @Test
    public void givenAExistWTWithExercisesRemoveAllExercise_whenUpdate_ReturnWtModified() {
        List<Exercise> exerciseList = workoutTable2EAdmin.getExerciseList();

        exerciseList.clear();
        WorkoutTable update = wtService.update(workoutTable2EAdmin);

        assertEquals("Algo ha ido mal", 0, update.getExerciseList().size());
    }

    @Test
    public void givenAExistWTId_whenDeleteWorkoutTable_thenIsNoLongerInDB() {
        Integer id = workoutTableClient.getId();
        wtService.delete(id);
        Optional<WorkoutTable> nothing = wtDao.findById(id);

        assertFalse(nothing.isPresent());
    }
}
