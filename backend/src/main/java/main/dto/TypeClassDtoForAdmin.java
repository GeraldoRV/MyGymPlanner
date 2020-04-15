package main.dto;



public class TypeClassDtoForAdmin {
    private Integer id;
    private String name;
    private String description;
    private Integer duration;
    private int nClassesDirected;
    private int nClassesAssigned;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getnClassesDirected() {
        return nClassesDirected;
    }

    public void setnClassesDirected(int nClassesDirected) {
        this.nClassesDirected = nClassesDirected;
    }

    public int getnClassesAssigned() {
        return nClassesAssigned;
    }

    public void setnClassesAssigned(int nClassesAssigned) {
        this.nClassesAssigned = nClassesAssigned;
    }
}
