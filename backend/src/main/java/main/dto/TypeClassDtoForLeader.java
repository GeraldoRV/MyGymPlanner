package main.dto;


public class TypeClassDtoForLeader {
    private Integer id;
    private String name;
    private int nClassesDirected;
    private int nClassesDirectedWithMonitor;

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

    public int getnClassesDirectedWithMonitor() {
        return nClassesDirectedWithMonitor;
    }

    public void setnClassesDirectedWithMonitor(int nClassesDirectedWithMonitor) {
        this.nClassesDirectedWithMonitor = nClassesDirectedWithMonitor;
    }
}
