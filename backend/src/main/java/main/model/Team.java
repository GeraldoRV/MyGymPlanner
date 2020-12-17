package main.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne
    private User leader;
    @OneToMany
    private List<User> members = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    @JsonManagedReference
    private List<TypeClass> typeClasses;

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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<TypeClass> getTypeClasses() {
        return typeClasses;
    }

    public void setTypeClasses(List<TypeClass> classes) {
        this.typeClasses = classes;
    }
}
