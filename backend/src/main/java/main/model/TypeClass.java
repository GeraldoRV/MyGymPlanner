package main.model;

import javax.persistence.*;

@Entity
public class TypeClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 1000)
    private String description;
    private Integer duration;
    private Integer NClassesDirected;

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

    public Integer getNClassesDirected() {
        return NClassesDirected;
    }

    public void setNClassesDirected(Integer NClassesDirected) {
        this.NClassesDirected = NClassesDirected;
    }
}
