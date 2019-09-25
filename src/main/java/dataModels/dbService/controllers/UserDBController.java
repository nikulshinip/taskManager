package dataModels.dbService.controllers;

import dataModels.User;
import dataModels.UserController;
import dataModels.dbService.dao.UserDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDBController implements UserController {

    private static int count = 0;
    private final SessionFactory sessionFactory;

    private UserDBController(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public static UserDBController init(SessionFactory sessionFactory){
        if(count != 0)
            return null;
        count++;
        return new UserDBController(sessionFactory);
    }

    public void addUser(User user) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        int id = dao.insertUser(user);
        transaction.commit();
        user.setId(id);
        session.close();
    }

    public void removeUser(String userName) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        dao.removeUser(userName);
        transaction.commit();
        session.close();
    }

    public void removeUser(User user) throws HibernateException{
        removeUser(user.getName());
    }

    public User getUser(String userName) throws HibernateException{
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        User user = dao.getUsers(userName);
        session.close();
        return user;
    }

    public void updateUser(User user) throws HibernateException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        dao.updateUser(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAllUsers() throws HibernateException{
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        List<User> users = dao.getAllUsers();
        session.close();
        return users;
    }
}
