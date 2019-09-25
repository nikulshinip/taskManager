package dataModels.dbService.controllers;

import dataModels.Project;
import dataModels.Task;
import dataModels.TaskController;
import dataModels.User;
import dataModels.dbService.dao.TaskDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDBController implements TaskController {
    private static int count = 0;
    private final SessionFactory sessionFactory;

    private TaskDBController(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public static TaskDBController init(SessionFactory sessionFactory){
        if(count != 0)
            return null;
        count++;
        return new TaskDBController(sessionFactory);
    }

    public Task getTask(String taskName) {
        Session session = sessionFactory.openSession();
        TaskDAO dao = new TaskDAO(session);
        Task task = dao.getTask(taskName);
        session.close();
        return task;
    }

    public void addTask(Task task) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TaskDAO dao = new TaskDAO(session);
        int id = dao.insertTask(task);
        transaction.commit();
        task.setId(id);
        session.close();
    }

    public void removeTask(Task task) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TaskDAO dao = new TaskDAO(session);
        dao.removeTask(task);
        transaction.commit();
        session.close();
    }

    public void updateTask(Task task) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TaskDAO dao = new TaskDAO(session);
        dao.updateTask(task);
        transaction.commit();
        session.close();
    }

    public List<Task> getAllTasks() throws HibernateException{
        Session session = sessionFactory.openSession();
        TaskDAO dao = new TaskDAO(session);
        List<Task> tasks = dao.getAllTasks();
        session.close();
        return tasks;
    }

    public List<Task> getAllTasksOnProject(Project project) {
        Session session = sessionFactory.openSession();
        TaskDAO dao = new TaskDAO(session);
        List<Task> tasks = dao.getTaskByProject(project);
        session.close();
        return tasks;
    }

    public List<Task> getAllTasksForUser(User user) {
        Session session = sessionFactory.openSession();
        TaskDAO dao = new TaskDAO(session);
        List<Task> tasks = dao.getTaskByUser(user);
        session.close();
        return tasks;
    }
    public List<Task> getAllTaskOnProjectForUser(Project project, User user){
        Session session = sessionFactory.openSession();
        TaskDAO dao = new TaskDAO(session);
        List<Task> tasks = dao.getTaskByUserAndProject(user, project);
        session.close();
        return tasks;
    }
}
