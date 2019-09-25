package dataModels.dbService.dao;

import dataModels.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO {

    private Session session;

    public UserDAO(Session session){
        this.session = session;
    }

    public User get(int id) throws HibernateException {
        return (User)session.get(User.class, id);
    }

    public User getUsers(String name) throws HibernateException{
        //Criteria criteria = session.createCriteria(User.class);
        //return ((User) criteria.add(Restrictions.eq("name", name)).uniqueResult());
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("name"), name));
        return session.createQuery(criteria).uniqueResult();
    }

    public List<User> getAllUsers()throws HibernateException{
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return session.createQuery(criteria).getResultList();
    }

    public int insertUser(User user) throws HibernateException{
        return (Integer) session.save(user);
    }

    public void removeUser(String name) throws HibernateException{
        session.delete(getUsers(name));
    }

    public void updateUser(User user) throws HibernateException{
        session.update(user);
    }

}
