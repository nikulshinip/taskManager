package dataModels.dbService.dao;

import dataModels.Project;
import dataModels.Task;
import dataModels.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDAO {

    private Session session;

    public TaskDAO(Session session){
        this.session = session;
    }

    public Task getTask(String name) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.where(builder.equal(root.get("name"), name));
        return session.createQuery(criteria).uniqueResult();
    }

    public List<Task> getAllTasks() throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        criteria.from(Task.class);
        return session.createQuery(criteria).getResultList();
    }

    public List<Task> getTaskByUser(User user) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.where(builder.equal(root.get("user"), user));
        return session.createQuery(criteria).getResultList();
    }

    public List<Task> getTaskByProject(Project project){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.where(builder.equal(root.get("project"), project));
        return session.createQuery(criteria).getResultList();
    }

    public List<Task> getTaskByUserAndProject(User user, Project project){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.where(builder.equal(root.get("project"), project),
                            builder.equal(root.get("user"), user));
        return session.createQuery(criteria).getResultList();
    }

    public  int insertTask(Task task) throws HibernateException{
        return (Integer) session.save(task);
    }

    public void removeTask(String taskName){
        session.delete(getTask(taskName));
    }

    public void removeTask(Task task) throws HibernateException{
        session.delete(task);
    }

    public void updateTask(Task task) throws HibernateException{
        session.update(task);
    }

}
