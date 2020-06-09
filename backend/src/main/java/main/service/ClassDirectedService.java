package main.service;

import main.dao.ClassDirectedDao;
import main.dao.UserDao;
import main.dto.UserTypeMonitorDto;
import main.exception.*;
import main.model.ClassDirected;
import main.model.ClassSchedule;
import main.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ClassDirectedService {
    private final ClassDirectedDao classDirectedDao;
    private final UserDao userDao;

    public ClassDirectedService(ClassDirectedDao classDirectedDao, UserDao userDao) {
        this.classDirectedDao = classDirectedDao;
        this.userDao = userDao;
    }

    public Optional<ClassDirected> getClassDirected(Integer id) {
        return classDirectedDao.findById(id);
    }

    public List<ClassDirected> getAllClassDirectedOfGym(Integer id) {
        return classDirectedDao.findAllByGym_Id(id);
    }

    public List<ClassDirected> getAllClassDirectedOfTypeClass(Integer id) {
        return classDirectedDao.findAllByTypeClass_Id(id);
    }

    public List<ClassDirected> getAllClassDirectedOfDayAndGym(String dayOfWeek, Integer id) {
        return classDirectedDao
                .findAllByClassSchedule_DayOfWeekAndGym_IdOrderByClassSchedule_StartTimeAsc(dayOfWeek, id);
    }

    public List<ClassDirected> getAllClassDirectedOfMonitorAndDay(Integer id, String dayOfWeek) {
        return classDirectedDao
                .findAllByAssignedMonitor_IdAndClassSchedule_DayOfWeekOrderByClassSchedule_StartTimeAsc(id, dayOfWeek);
    }

    public ClassDirected assignMonitor(UserTypeMonitorDto monitor, Integer classId) {
        ClassDirected classDirected = classDirectedDao.findById(classId)
                .orElseThrow(ClassDirectedNotFoundException::new);
        User monitorDB = userDao.findByIdAndRole(monitor.getId(),"monitor").orElseThrow(
                ()-> new UserNotFoundException("The monitor with the name " + monitor.getName() + " not exist"));

        classDirected.setAssignedMonitor(monitorDB);

        return classDirectedDao.save(classDirected);
    }

    public boolean reserveAClass(ClassDirected classDirected, Integer userId, Date date) throws NotValidDayToReserveException, ClassDirectedFullException, TheClientIsInTheClassException {
        checkClass(classDirected, date);
        User client = getUser(userId);
        isTheClientInTheClass(classDirected.getId(), client);
        return addClientInClientList(classDirected, client);
    }

    public boolean addClientToClass(ClassDirected classDirected, Integer clientId) throws TheClientIsInTheClassException {
        User client = getUser(clientId);
        isTheClientInTheClass(classDirected.getId(), client);
        return addClientInClientList(classDirected, client);
    }

    private void checkClass(ClassDirected classToCheck, Date date) throws NotValidDayToReserveException, ClassDirectedFullException {
        if (!checkTheDayIsToday(classToCheck.getClassSchedule(), date)) {
            throw new NotValidDayToReserveException();
        }
        if (isFullTheClass(classToCheck)) {
            throw new ClassDirectedFullException();
        }
    }

    private void isTheClientInTheClass(Integer classId, User user) throws TheClientIsInTheClassException {
        Boolean exists = classDirectedDao.existsByIdAndClientListContains(classId, user);

        if (exists) {
            throw new TheClientIsInTheClassException();
        }
    }

    private boolean addClientInClientList(ClassDirected classDirected, User user) {
        ClassDirected theClassInDB = getTheClassInDB(classDirected.getId());

        theClassInDB.getClientList().add(user);

        setFull(theClassInDB);

        ClassDirected save = classDirectedDao.save(theClassInDB);
        return (save != null);

    }

    private boolean checkTheDayIsToday(ClassSchedule classSchedule, Date date) {
        Date today = getCurrentDay(date);

        String todayDayOfWeek = getTodayDayOfWeek(today);
        if (todayDayOfWeek.equals(classSchedule.getDayOfWeek())) {
            return checkTime(today, classSchedule.getStartTime());
        }
        return false;
    }

    private boolean isFullTheClass(ClassDirected classToCheck) {
        return classDirectedDao.existsByIdAndIsFull(classToCheck.getId(), true);
    }

    private void setFull(ClassDirected theClassInDB) {
        if (theClassInDB.getClientList().size() == theClassInDB.getCapacity())
            theClassInDB.setFull(true);
    }

    private ClassDirected getTheClassInDB(Integer id) {
        Optional<ClassDirected> byId = classDirectedDao.findById(id);
        return byId.orElse(null);
    }

    private Date getCurrentDay(Date date) {
        if (date != null) return date;
        return new Date();
    }

    private boolean checkTime(Date today, String startTime) {
        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String currentTime = simpleDateFormat.format(today);
        return compareMint(startTime, currentTime);

    }

    private String getTodayDayOfWeek(Date today) {
        String pattern = "EEEEE";
        Locale locale = new Locale("en", "UK");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);

        return simpleDateFormat.format(today);
    }

    private boolean compareMint(String startTime, String currentTime) {
        String[] currentTimeArray = currentTime.split(":");
        String[] startTimeArray = startTime.split(":");

        int nStartTime = Integer.parseInt(startTimeArray[0]) * 60 + Integer.parseInt(startTimeArray[1]);
        int nCurrentTime = Integer.parseInt(currentTimeArray[0]) * 60 + Integer.parseInt(currentTimeArray[1]);

        return nStartTime - nCurrentTime >= 15;
    }

    private User getUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }
}
