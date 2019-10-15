package main.service;

import main.dao.ClassDirectedDao;
import main.model.ClassDirected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassDirectedService {
    @Autowired
    private ClassDirectedDao classDirectedDao;

    public List<ClassDirected> getAllClassDirectedOfGym(Integer id) {
        return classDirectedDao.findAllByGym_Id(id);
    }

    public List<ClassDirected> getAllClassDirectedOfDayAndGym(String dayOfWeek, Integer id){
        return classDirectedDao.findAllByClassSchedule_DayOfWeekAndGym_IdOrderByClassSchedule_StartTimeAsc(dayOfWeek,id);
    }
}
