package dataModels.dbService.controllers;

import dataModels.Project;
import dataModels.ProjectController;
import dataModels.dbService.dao.ProjectDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDBController implements ProjectController {
    private static int count = 0;
    private final SessionFactory sessionFactory;

    private ProjectDBController(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public static ProjectDBController init(SessionFactory sessionFactory){
        if(count != 0)
            return null;
        count++;
        return new ProjectDBController(sessionFactory);
    }

    public void addProject(Project project) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectDAO dao = new ProjectDAO(session);
        int id = dao.insertProject(project);
        transaction.commit();
        project.setId(id);
        session.close();
    }

    public void removeProject(String projectName) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectDAO dao = new ProjectDAO(session);
        dao.removeProject(projectName);
        transaction.commit();
        session.close();
    }

    public void removeProject(Project project) throws HibernateException{
        removeProject(project.getName());
    }

    public Project getProject(String projectName) throws HibernateException{
        Session session = sessionFactory.openSession();
        ProjectDAO dao = new ProjectDAO(session);
        Project project = dao.getProject(projectName);
        session.close();
        return project;
    }

    public void updateProject(Project project) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectDAO dao = new ProjectDAO(session);
        dao.updateProject(project);
        transaction.commit();
        session.close();
    }

    public List<Project> getAllProjects() throws HibernateException{
        Session session = sessionFactory.openSession();
        ProjectDAO dao = new ProjectDAO(session);
        List<Project> projects = dao.getAllProjects();
        session.close();
        return projects;
    }
}
