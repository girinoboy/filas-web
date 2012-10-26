package br.com.config;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.tables.Event;
import br.com.tables.MockObject;
import br.com.tables.Usuario;

@SuppressWarnings("deprecation")
public class HibernateUtil {

   // private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    static
    {
        try
        {
        	sessionFactory =  new AnnotationConfiguration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
            .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
            //config.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:.demodb");
            .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:./data/contratos;shutdown=true")
            .setProperty("hibernate.connection.username", "sa")
            .setProperty("hibernate.connection.password", "")
           //config.setProperty("hibernate.default_schema", "dbo");
            .setProperty("hibernate.connection.pool_size", "1")
            .setProperty("hibernate.connection.autocommit", "true")
            .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")
            .setProperty("hibernate.hbm2ddl.auto", "update")
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
            .setProperty("hibernate.current_session_context_class", "thread")
 
            // Add your mapped classes here:
            .addAnnotatedClass(Event.class)
            .addAnnotatedClass(MockObject.class)
            .addAnnotatedClass(Usuario.class)
 
            .buildSessionFactory();
            
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
    	if(sessionFactory == null)// Create the SessionFactory from hibernate.cfg.xml
    		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        return sessionFactory;
    }
    
    public static Session getSession()
            throws HibernateException {
        return sessionFactory.openSession();
    }

}