package dataModels;

import java.util.List;

public interface TaskController {
    Task getTask(String taskName);
    void addTask(Task task);
    void removeTask(Task task);
    void updateTask(Task task);
    List<Task> getAllTasks();
    List<Task> getAllTasksOnProject(Project project);
    List<Task> getAllTasksForUser(User user);
    List<Task> getAllTaskOnProjectForUser(Project project, User user);
}
