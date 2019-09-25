package dataModels;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects")
public class Project implements Serializable {
    @Id
    @Column(name = "projectId")
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    private String description;

    public Project(){}
    public Project(String name){
        setName(name);
        setDescription("");
    }
    public Project(String name, String description){
        setName(name);
        setDescription(description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{ProjectDataSet : id = '" + id +
                "' name = '" + name +
                "' description = '" + description +
                "'};";
    }
}
