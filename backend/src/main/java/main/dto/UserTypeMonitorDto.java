package main.dto;

import main.model.WorkingHours;

public class UserTypeMonitorDto {
    private Integer id;
    private String name;
    private String userName;
    private WorkingHours workingHours;

    private Boolean isLeader = false;
    private Integer nClassAssigned;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }

    public Integer getnClassAssigned() {
        return nClassAssigned;
    }

    public void setnClassAssigned(Integer nClassAssigned) {
        this.nClassAssigned = nClassAssigned;
    }
}
