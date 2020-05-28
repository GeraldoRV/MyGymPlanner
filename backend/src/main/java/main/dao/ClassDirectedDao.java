package main.dao;

import main.model.ClassDirected;
import main.model.TypeClass;
import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDirectedDao extends CrudRepository<ClassDirected, Integer> {
    List<ClassDirected> findAllByGym_Id(Integer id);

    List<ClassDirected> findAllByTypeClass_Id(Integer id);

    List<ClassDirected> findAllByClassSchedule_DayOfWeekAndGym_IdOrderByClassSchedule_StartTimeAsc(String dayOfWeek, Integer id);

    List<ClassDirected> findAllByAssignedMonitor_IdAndClassSchedule_DayOfWeekOrderByClassSchedule_StartTimeAsc(Integer id, String dayOfWeek);

    Boolean existsByIdAndClientListContains(Integer id, User user);

    Boolean existsByIdAndIsFull(Integer id, Boolean isFull);

    int countAllByTypeClass(TypeClass typeClass);

    int countAllByTypeClassAndAssignedMonitorNotNull(TypeClass typeClass);

}
