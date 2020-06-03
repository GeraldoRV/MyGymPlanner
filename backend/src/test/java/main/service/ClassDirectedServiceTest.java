package main.service;

import main.dao.ClassDirectedDao;
import main.dao.GymDao;
import main.dao.UserDao;
import main.dto.UserTypeMonitorDto;
import main.exception.*;
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
    private ClassDirected fullClass;
    private User clientInTheClass;
    private ClassDirected notFullClass;

    @Before
    public void setUp() {
        Gym gym = new Gym();
        gym.setName("Gym without classes");
        gymWithoutClasses = gymDao.save(gym);

        Gym gym2 = new Gym();
        gym2.setName("Gym with classes");
        gymWithClasses = gymDao.save(gym2);

        ClassDirected classDirected = new ClassDirected();
        classDirected.setCapacity(1);
        classDirected.setGym(gymWithClasses);
        User user1 = new User();
        user1.setRole("client");
        User client = userDao.save(user1);
        classDirected.getClientList().add(client);
        classDirected.setFull(true);

        ClassDirected classDirected2 = new ClassDirected();
        classDirected2.setCapacity(21);
        classDirected2.setGym(gymWithClasses);

        User clientNotSave = new User();
        clientNotSave.setRole("client");
        clientNotSave.setName("client");
        clientInTheClass = userDao.save(clientNotSave);
        classDirected2.getClientList().add(clientInTheClass);

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

        User monitorNotSave = new User();
        monitorNotSave.setRole("monitor");
        monitorNotSave.setName("monitor");
        monitor = userDao.save(monitorNotSave);

        classDirected.setAssignedMonitor(monitor);
        fullClass = classDirectedDao.save(classDirected);
        notFullClass = classDirectedDao.save(classDirected2);
        classWithNotClient = classDirectedDao.save(classDirected3);


    }

    @Test
    public void givenAExistClassId_whenGetClassDirected_returnTheClass() {
        Optional<ClassDirected> classDirected = classDirectedService.getClassDirected(fullClass.getId());
        assertTrue(classDirected.isPresent());
        ClassDirected classDirected1 = classDirected.get();
        assertEquals(fullClass.getId(), classDirected1.getId());
    }

    @Test
    public void givenANotExistClassId_whenGetClassDirected_returnNotPresent() {
        Optional<ClassDirected> classDirected = classDirectedService.getClassDirected(1500);
        assertFalse(classDirected.isPresent());
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
    public void givenAExistClassWithoutClientsAndOneNewClientInTheCorrectTimeReserve_whenReserveAClass_returnTrue() throws ParseException {
        User client = new User();
        client.setRole("client");
        User save = userDao.save(client);
        Date date = getDate("2019-11-12 19:45:00.0");
        boolean add = false;
        try {
            add = classDirectedService.reserveAClass(classWithNotClient, save.getId(), date);
        } catch (NotValidDayToReserveException | ClassDirectedFullException | TheClientIsInTheClassException e) {
            e.printStackTrace();
        }
        assertTrue("Something wrong", add);
        Optional<ClassDirected> byId = classDirectedDao.findById(classWithNotClient.getId());
        if (byId.isPresent()) {
            ClassDirected classDirected = byId.get();
            assertEquals(1, classDirected.getClientList().size());
        }

    }

    @Test(expected = ClassDirectedFullException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void givenAExistFullClassAndOneNewClientInTheCorrectTimeReserve_whenReserveAClass_thenExpectationSatisfied() throws ParseException, NotValidDayToReserveException, TheClientIsInTheClassException, ClassDirectedFullException {
        User client = new User();
        client.setRole("client");
        User save = userDao.save(client);
        Date date = getDate("2019-11-11 19:45:00.0");
        classDirectedService.reserveAClass(fullClass, save.getId(), date);
    }

    @Test(expected = NotValidDayToReserveException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void givenAExistsNotFullClassAndOneNewClientInTheCorrectDayButNotInTheCorrectTime_whenReserveAClass_thenExpectationSatisfied() throws ParseException, NotValidDayToReserveException, TheClientIsInTheClassException, ClassDirectedFullException {
        User client = new User();
        client.setRole("client");
        User save = userDao.save(client);
        Date date = getDate("2019-11-12 20:00:00.0");
        classDirectedService.reserveAClass(classWithNotClient, save.getId(), date);
    }

    @Test(expected = NotValidDayToReserveException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void givenAExistsNotFullClassAndOneNewClientNotInTheCorrectDay_whenReserveAClass_thenExpectationSatisfied() throws ParseException, NotValidDayToReserveException, TheClientIsInTheClassException, ClassDirectedFullException {
        User client = new User();
        client.setRole("client");
        User save = userDao.save(client);
        Date date = getDate("2019-11-11 19:45:00.0");
        classDirectedService.reserveAClass(classWithNotClient, save.getId(), date);
    }

    @Test(expected = TheClientIsInTheClassException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void givenAExistsNotFullClassAndOneClientInTheClassYetButInTheCorrectTime_whenReserveAClass_thenExpectationSatisfied() throws ParseException, NotValidDayToReserveException, TheClientIsInTheClassException, ClassDirectedFullException {
        Date date = getDate("2019-11-12 19:45:00.0");
        classDirectedService.reserveAClass(notFullClass, clientInTheClass.getId(), date);
    }

    @Test(expected = ClassDirectedNotFoundException.class)
    public void givenNotExistClassDirected_whenAssignMonitor_thenExpectationSatisfied() {
        UserTypeMonitorDto userTypeMonitorDto = new UserTypeMonitorDto();
        userTypeMonitorDto.setId(1);
        classDirectedService.assignMonitor(userTypeMonitorDto, 1200);

    }

    @Test(expected = UserNotFoundException.class)
    public void givenExistClassDirectedAndNotExistUser_whenAssignMonitor_thenExpectationSatisfied() {
        UserTypeMonitorDto userTypeMonitorDto = new UserTypeMonitorDto();
        userTypeMonitorDto.setId(789);
        classDirectedService.assignMonitor(userTypeMonitorDto, 1);

    }

    @Test(expected = UserNotFoundException.class)
    public void givenExistClassDirectedAndExistUserButNotMonitor_whenAssignMonitor_thenExpectationSatisfied() {
        User user = new User();
        user.setName("Not monitor");
        user.setUserName("notmonitor");
        user.setRole("notmonitor");
        userDao.save(user);
        UserTypeMonitorDto userTypeMonitorDto = new UserTypeMonitorDto();
        userTypeMonitorDto.setId(user.getId());

        classDirectedService.assignMonitor(userTypeMonitorDto, 1);

    }

    @Test
    public void givenExistClassDirectedAndExistMonitor_whenAssignMonitor_thenReturnTheClassDirectedWithTheMonitor() {
        User user = new User();
        user.setName("Monitor");
        user.setUserName("monitor");
        user.setRole("monitor");
        userDao.save(user);
        UserTypeMonitorDto userTypeMonitorDto = new UserTypeMonitorDto();
        userTypeMonitorDto.setId(user.getId());

        ClassDirected classDirected = classDirectedService.assignMonitor(userTypeMonitorDto, 1);

        Integer one = 1;

        assertEquals(one, classDirected.getId());
        assertNotNull(classDirected.getAssignedMonitor());
        assertEquals(user.getId(), classDirected.getAssignedMonitor().getId());


    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(date);
    }

}
