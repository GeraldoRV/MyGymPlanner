package main.dto;

import javax.validation.constraints.NotNull;

public class TypeClassDtoForAdmin {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private int nClassesDirected;
    private TeamDtoForAdmin team;

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

    public int getnClassesDirected() {
        return nClassesDirected;
    }

    public void setnClassesDirected(int nClassesDirected) {
        this.nClassesDirected = nClassesDirected;
    }

    public TeamDtoForAdmin getTeam() {
        return team;
    }

    public void setTeam(TeamDtoForAdmin team) {
        this.team = team;
    }
}
