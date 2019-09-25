package dataModels.dbService;

import dataModels.Project;
import dataModels.Task;
import dataModels.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private static int count = 0;
    private static final String hibernate_show_sql = "false";//"true";
    private static final String hibernate_hbm2ddl_auto = "update"; //"create";

    private final SessionFactory sessionFactory;

    private DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }
    public static DBService init(){
        if(count != 0)
            return null;
        count++;
        return new DBService();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Configuration getH2Configuration(){
        Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class );
        configuration.addAnnotatedClass(Project.class );
        configuration.addAnnotatedClass(Task.class );

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
