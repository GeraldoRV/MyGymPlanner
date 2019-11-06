package main.service;

import main.dao.ClassDirectedDao;
import main.model.ClassDirected;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            Optional<ClassDirected> byId = classDirectedDao.findById(classDirected.getId());
            if (byId.isPresent()) {
                ClassDirected classDirected1 = byId.get();
                classDirected1.getClientList().add(user);
                ClassDirected save = classDirectedDao.save(classDirected1);
                return (save != null);
            }

        }
        return false;
    }

    private boolean isInTheClass(Integer classDirectedId, User user) {
        return classDirectedDao.existsByIdAndClientListContains(classDirectedId, user);
    }
}
