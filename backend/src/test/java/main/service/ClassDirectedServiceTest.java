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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private ClassDirected classWithNotClient;

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

        ClassDirected classDirected3 = new ClassDirected();
        classDirected3.setCapacity(21);
        classDirected3.setGym(gymWithClasses);

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

        ClassSchedule classSchedule3 = new ClassSchedule();
        classSchedule3.setDayOfWeek("Tuesday");
        classSchedule3.setStartTime("20:00");
        classSchedule3.setEndTime("20:45");
        classDirected3.setClassSchedule(classSchedule3);

        User user = new User();
        user.setRole("monitor");
        user.setName("monitor");
        monitor = userDao.save(user);
        classDirected.setAssignedMonitor(monitor);
        classDirectedDao.save(classDirected);
        classDirectedDao.save(classDirected2);
        classWithNotClient = classDirectedDao.save(classDirected3);


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

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void givenAExistClassWithoutClientsAndOneNewClientInTheCorrectTimeReserve_whenAddClientInAClass_returnTrue() throws ParseException {
        User client = new User();
        client.setRole("client");
        User save = userDao.save(client);
        Date date = getDate("2019-11-12 19:45:00.0");
        boolean add = classDirectedService.addClientInAClass(classWithNotClient, save.getId(), date);
        assertTrue("Algo fue mal", add);
        Optional<ClassDirected> byId = classDirectedDao.findById(classWithNotClient.getId());
        if (byId.isPresent()) {
            ClassDirected classDirected = byId.get();
            assertEquals(1, classDirected.getClientList().size());
        }

    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(date);
    }

}
