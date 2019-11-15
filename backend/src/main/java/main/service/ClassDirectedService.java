package main.service;

import main.dao.ClassDirectedDao;
import main.exception.ClassDirectedFullException;
import main.exception.NotValidDayToReserveException;
import main.exception.TheClientIsInTheClassException;
import main.model.ClassDirected;
import main.model.ClassSchedule;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClassDirectedService {
    @Autowired
    private ClassDirectedDao classDirectedDao;

    public List<ClassDirected> getAllClassDirectedOfGym(Integer id) {
        return classDirectedDao.findAllByGym_Id(id);
    }

    public List<ClassDirected> getAllClassDirectedOfDayAndGym(String dayOfWeek, Integer id) {
        return classDirectedDao.findAllByClassSchedule_DayOfWeekAndGym_IdOrderByClassSchedule_StartTimeAsc(dayOfWeek, id);
    }

    public List<ClassDirected> getAllClassDirectedOfMonitorAndDay(Integer id, String dayOfWeek) {
        return classDirectedDao.findAllByAssignedMonitor_IdAndClassSchedule_DayOfWeekOrderByClassSchedule_StartTimeAsc(id, dayOfWeek);
    }

    public boolean addClientInAClass(ClassDirected classDirected, Integer userId, Date date) throws NotValidDayToReserveException, ClassDirectedFullException, TheClientIsInTheClassException {
        checkClass(classDirected, date);
        User user = new User();
        user.setId(userId);
        isTheClientInTheClass(classDirected.getId(), user);
        return addClientInClientList(classDirected, user);

    }

    private void checkClass(ClassDirected classToCheck, Date date) throws NotValidDayToReserveException, ClassDirectedFullException {
        if (!checkTheDayIsToday(classToCheck.getClassSchedule(), date)) {
            throw new NotValidDayToReserveException();
        }
        if (isFullTheClass(classToCheck)) {
            throw new ClassDirectedFullException();
        }
    }

    private boolean isFullTheClass(ClassDirected classToCheck) {
        return classDirectedDao.existsByIdAndIsFull(classToCheck.getId(), true);
    }

    private boolean checkTheDayIsToday(ClassSchedule classSchedule, Date date) {
        Date today = getCurrentDay(date);

        String pattern = "EEEEE";
        Locale locale = new Locale("en", "UK");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, locale);
        String todayDayOfWeek = simpleDateFormat.format(today);

        if (todayDayOfWeek.equals(classSchedule.getDayOfWeek())) {
            return checkTime(today, classSchedule.getStartTime());
        }
        return false;
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

    private boolean compareMint(String startTime, String currentTime) {
        String[] currentTimeArray = currentTime.split(":");
        String[] startTimeArray = startTime.split(":");

        int nStartTime = Integer.parseInt(startTimeArray[0]) * 60 + Integer.parseInt(startTimeArray[1]);
        int nCurrentTime = Integer.parseInt(currentTimeArray[0]) * 60 + Integer.parseInt(currentTimeArray[1]);

        return nStartTime - nCurrentTime >= 15;
    }

}
