package main.dto;

import main.model.ClassSchedule;
import main.model.User;

public class ClassDirectedDtoToAssign {
    private Integer id;
    private User assignedMonitor;
    private ClassSchedule classSchedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAssignedMonitor() {
        return assignedMonitor;
    }

    public void setAssignedMonitor(User assignedMonitor) {
        this.assignedMonitor = assignedMonitor;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }
}
