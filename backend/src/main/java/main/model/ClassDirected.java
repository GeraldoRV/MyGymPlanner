package main.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClassDirected {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TypeClass typeClass;
    @ManyToOne
    private User assignedMonitor;
    private Integer capacity;
    @ManyToMany
    private List<User> clientList;
    @Embedded
    private ClassSchedule classSchedule;

    @ManyToOne
    private Gym gym;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeClass getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(TypeClass typeClass) {
        this.typeClass = typeClass;
    }

    public User getAssignedMonitor() {
        return assignedMonitor;
    }

    public void setAssignedMonitor(User assignedMonitor) {
        this.assignedMonitor = assignedMonitor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<User> getClientList() {
        return clientList;
    }

    public void setClientList(List<User> clientList) {
        this.clientList = clientList;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }
}
