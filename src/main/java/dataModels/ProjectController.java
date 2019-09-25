package dataModels;

import java.util.List;

public interface ProjectController {
    Project getProject(String projectName);
    void addProject(Project project);
    void removeProject(Project project);
    void updateProject(Project project);
    List<Project> getAllProjects();
}
