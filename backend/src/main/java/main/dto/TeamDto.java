package main.dto;

import java.util.List;

public class TeamDto {
    private Integer id;
    private String name;
    private UserTypeMonitorDto leader;
    private List<UserTypeMonitorDto> members;
    private List<TypeClassDtoForLeader> typeClasses;

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

    public UserTypeMonitorDto getLeader() {
        return leader;
    }

    public void setLeader(UserTypeMonitorDto leader) {
        this.leader = leader;
    }

    public List<UserTypeMonitorDto> getMembers() {
        return members;
    }

    public void setMembers(List<UserTypeMonitorDto> members) {
        this.members = members;
    }

    public List<TypeClassDtoForLeader> getTypeClasses() {
        return typeClasses;
    }

    public void setTypeClasses(List<TypeClassDtoForLeader> typeClasses) {
        this.typeClasses = typeClasses;
    }
}
