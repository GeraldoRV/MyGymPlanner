package main.service;

import main.dao.ClassDirectedDao;
import main.model.ClassDirected;
import main.model.ClassSchedule;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
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

    public boolean addClientInAClass(ClassDirected classDirected, Integer userId) {
        User user = new User();
        user.setId(userId);
        if (!isInTheClass(classDirected.getId(), user)) {
            if (checkTheDayIsToday(classDirected.getClassSchedule())) {
                Optional<ClassDirected> byId = classDirectedDao.findById(classDirected.getId());
                if (byId.isPresent()) {
                    ClassDirected classDirected1 = byId.get();


                    if (classDirected.getClientList() == null) {
                        classDirected1.setClientList(new ArrayList<>());

                    }
                    classDirected1.getClientList().add(user);
                    ClassDirected save = classDirectedDao.save(classDirected1);
                    return (save != null);
                }
            }
        }
        return false;
    }

    private boolean checkTheDayIsToday(ClassSchedule classSchedule) {
        Date today = new Date();
        String pattern ="EEEEE";
        Locale locale = new Locale("en", "UK");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, locale);
        String todayDayOfWeek = simpleDateFormat.format(today);

        return todayDayOfWeek.equals(classSchedule.getDayOfWeek());
    }

    private boolean isInTheClass(Integer classDirectedId, User user) {
        return classDirectedDao.existsByIdAndClientListContains(classDirectedId, user);
    }
}
