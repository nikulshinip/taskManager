package dataModels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    private String type;
    private String priority;
    private String description;
    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private Date date;

    public Task(){}
    public Task(String name, String type, String priority, String description, Project project, User user){
        setName(name);
        setType(type);
        setPriority(priority);
        setDescription(description);
        setProject(project);
        setUser(user);
        setDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "{TaskDataSet : id = '" + id +
                "' name = '" + name +
                "' type = '" + type +
                "' priority = '" + priority +
                "' description = '" + description +
                "' project = '" + project +
                "' user = '" + user +
                "' date = '" + date +
                "'};";
    }
}
