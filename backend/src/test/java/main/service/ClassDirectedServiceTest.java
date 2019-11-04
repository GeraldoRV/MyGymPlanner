package main.service;

import main.dao.ClassDirectedDao;
import main.dao.GymDao;
import main.dao.UserDao;
import main.model.ClassDirected;
import main.model.ClassSchedule;
import main.model.Gym;
import main.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClassDirectedServiceTest {
    @Autowired
    private ClassDirectedService classDirectedService;
    @Autowired
    private GymDao gymDao;
    @Autowired
    private ClassDirectedDao classDirectedDao;
    @Autowired
    private UserDao userDao;
    private Gym gymWithoutClasses;
    private Gym gymWithClasses;
    private User monitor;

    @Before
    public void setUp() throws Exception {
        Gym gym = new Gym();
        gym.setName("Gym without classes");
        gymWithoutClasses = gymDao.save(gym);

        Gym gym2 = new Gym();
        gym2.setName("Gym with classes");
        gymWithClasses = gymDao.save(gym2);

        ClassDirected classDirected = new ClassDirected();
        classDirected.setCapacity(21);
        classDirected.setGym(gymWithClasses);

        ClassDirected classDirected2 = new ClassDirected();
        classDirected2.setCapacity(21);
        classDirected2.setGym(gymWithClasses);

        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setDayOfWeek("Monday");
        classSchedule.setStartTime("20:00");
        classSchedule.setEndTime("20:45");
        classDirected.setClassSchedule(classSchedule);

        ClassSchedule classSchedule2 = new ClassSchedule();
        classSchedule2.setDayOfWeek("Tuesday");
        classSchedule2.setStartTime("20:00");
        classSchedule2.setEndTime("20:45");
        classDirected2.setClassSchedule(classSchedule2);

        User user = new User();
        user.setRole("monitor");
        user.setName("monitor");
        monitor = userDao.save(user);
        classDirected.setAssignedMonitor(monitor);
        classDirectedDao.saveAll(Arrays.asList(classDirected, classDirected2));
    }

    @Test
    public void givenANotExistGym_whenGetAllClassDirectedOfGym_returnAEmptyList() {
        List<ClassDirected> allClassDirectedOfGym = classDirectedService.getAllClassDirectedOfGym(200000);
        assertTrue(allClassDirectedOfGym.isEmpty());
    }

    @Test
    public void givenAExistGymWithNotClasses_whenGetAllClassDirectedOfGym_returnAEmptyList() {
        List<ClassDirected> allClassDirectedOfGym = classDirectedService.getAllClassDirectedOfGym(gymWithoutClasses.getId());
        assertTrue(allClassDirectedOfGym.isEmpty());
    }

    @Test
    public void givenAExistGymWithClasses_whenGetAllClassDirectedOfGym_returnANotEmptyList() {
        List<ClassDirected> allClassDirectedOfGym = classDirectedService.getAllClassDirectedOfGym(gymWithClasses.getId());
        assertFalse(allClassDirectedOfGym.isEmpty());
    }


    @Test
    public void givenACorrectDayOfWeekAndAExistGymWithClassesInThatDay_whenGetAllClassDirectedOfDayAndGym_returnANotEmptyList() {
        List<ClassDirected> classes = classDirectedService.getAllClassDirectedOfDayAndGym("Monday", gymWithClasses.getId());
        assertFalse(classes.isEmpty());
    }

    @Test
    public void givenACorrectDayOfWeekAndAExistGymWithNotClassesInThatDay_whenGetAllClassDirectedOfDayAndGym_returnAEmptyList() {
        List<ClassDirected> classes = classDirectedService.getAllClassDirectedOfDayAndGym("Wednesday", gymWithClasses.getId());
        assertTrue(classes.isEmpty());
    }

    @Test
    public void givenANotCorrectDayOfWeekAndAExistGym_whenGetAllClassDirectedOfDayAndGym_returnAEmptyList() {
        List<ClassDirected> classes = classDirectedService.getAllClassDirectedOfDayAndGym("Tusday", gymWithClasses.getId());
        assertTrue(classes.isEmpty());
    }


    @Test
    public void givenAExistMonitorAndACorrectDayOfWeekWithClassesInThatDayWithThatMonitor_whenGetAllClassDirectedOfMonitorAndDay_returnANotEmptyWithTheClassesAssigned() {
        List<ClassDirected> classes = classDirectedService.getAllClassDirectedOfMonitorAndDay(monitor.getId(), "Monday");
        assertFalse(classes.isEmpty());
        assertEquals(1, classes.size());
    }

    @Test
    public void givenAExistMonitorAndACorrectDayOfWeekWithClassesInThatDayWithoutThatMonitor_whenGetAllClassDirectedOfMonitorAndDay_returnAEmptyWith() {
        List<ClassDirected> classes = classDirectedService.getAllClassDirectedOfMonitorAndDay(monitor.getId(), "Tuesday");
        assertTrue(classes.isEmpty());
    }

}
