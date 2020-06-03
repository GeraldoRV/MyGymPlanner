package main.dto;

import main.model.ClassSchedule;
import main.model.User;

public class ClassDirectedDtoToAssign {
    private Integer id;
    private UserTypeMonitorDto assignedMonitor;
    private ClassSchedule classSchedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserTypeMonitorDto getAssignedMonitor() {
        return assignedMonitor;
    }

    public void setAssignedMonitor(UserTypeMonitorDto assignedMonitor) {
        this.assignedMonitor = assignedMonitor;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }
}
