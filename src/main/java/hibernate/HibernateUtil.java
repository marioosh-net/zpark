package hibernate;

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 * Startup Hibernate and provide access to the singleton SessionFactory
 * https://www.hibernate.org/114.html
 * 
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			System.out.println("sessionFactory....");
			//sessionFactory = new Configuration().configure().buildSessionFactory();
			// na adnotacjach
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		// Alternatively, we could look up in JNDI here
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
