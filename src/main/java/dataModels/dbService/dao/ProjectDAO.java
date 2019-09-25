package dataModels.dbService.dao;

import dataModels.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectDAO {

    private Session session;

    public ProjectDAO(Session session){
        this.session = session;
    }

    public Project getProject(String name) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        Root<Project> root = criteria.from(Project.class);
        criteria.where(builder.equal(root.get("name"), name));
        return session.createQuery(criteria).uniqueResult();
    }

    public List<Project> getAllProjects() throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        criteria.from(Project.class);
        return session.createQuery(criteria).getResultList();
    }

    public int insertProject(Project project) throws HibernateException{
        return (Integer) session.save(project);
    }

    public void removeProject(String name) throws HibernateException{
        session.delete(getProject(name));
    }

    public void updateProject(Project project) throws HibernateException{
        session.update(project);
    }
}
