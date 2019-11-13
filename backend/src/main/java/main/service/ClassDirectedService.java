package main.service;

import main.dao.ClassDirectedDao;
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

    public boolean addClientInAClass(ClassDirected classDirected, Integer userId, Date date) {
        if (checkClass(classDirected, date)) {
            User user = new User();
            user.setId(userId);
            if (!isTheClientInTheClass(classDirected.getId(), user)) {
                return addClientInClientList(classDirected, user);
            }
        }
        return false;
    }

    private boolean checkClass(ClassDirected classToCheck, Date date) {
        if (isFullTheClass(classToCheck)) return false;
        return checkTheDayIsToday(classToCheck.getClassSchedule(), date);
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

    private boolean isTheClientInTheClass(Integer classId, User user) {
        return classDirectedDao.existsByIdAndClientListContains(classId, user);
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
        String[] currentTimeArray = currentTime.split(":");
        String[] startTimeArray = startTime.split(":");
        if (compareHour(startTimeArray[0], currentTimeArray[0])) {
            return compareMint(startTimeArray[1], currentTimeArray[1]);
        }
        return false;
    }

    private boolean compareMint(String startTime, String currentTime) {
        int nStartTime, nCurrentTime;
        if (startTime.equals("00")) {
            nStartTime = 60;
        } else {
            nStartTime = Integer.parseInt(startTime);
        }
        if (currentTime.equals("00")) {
            nCurrentTime = 60;
        } else {
            nCurrentTime = Integer.parseInt(currentTime);
        }

        return nStartTime - nCurrentTime >= 15;
    }

    private boolean compareHour(String startTime, String currentTime) {
        int nStartTime = Integer.parseInt(startTime);
        int nCurrentTime = Integer.parseInt(currentTime);
        return nCurrentTime <= nStartTime;
    }
}
