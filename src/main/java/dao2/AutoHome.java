package dao2;

// Generated 2009-11-28 22:17:34 by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Auto.
 * @see dao2.Auto
 * @author Hibernate Tools
 */
public class AutoHome {

	private static final Log log = LogFactory.getLog(AutoHome.class);

	private final SessionFactory sessionFactory = hibernate.HibernateUtil.getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Auto transientInstance) {
		log.debug("persisting Auto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Auto instance) {
		log.debug("attaching dirty Auto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Auto instance) {
		log.debug("attaching clean Auto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Auto persistentInstance) {
		log.debug("deleting Auto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Auto merge(Auto detachedInstance) {
		log.debug("merging Auto instance");
		try {
			Auto result = (Auto) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Auto findById(int id) {
		log.debug("getting Auto instance with id: " + id);
		try {
			Auto instance = (Auto) sessionFactory.getCurrentSession().get(
					"dao2.Auto", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Auto instance) {
		log.debug("finding Auto instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"dao2.Auto").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Auto> findAll2() {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List autos = session.createQuery("from Auto").list();
		session.getTransaction().commit();
		session.close();
		return autos;
	}

	public List<AutoClient> findAll() {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		//List autos = session.createQuery("from Auto").list();
        //List autos = session.createQuery("select a.type as type, a.nr as nr, c.surname as cname from Auto as a, Client as c where c.id = a.idClient").list();
		List autos = session.createQuery("from Auto as a, Client as c where c.idClient = a.idClient").list();
		session.getTransaction().commit();
		session.close();
		
		ArrayList al = new ArrayList();
		for(Object o: autos) {
			Object[] o2 = (Object[])o;
			AutoClient ac = new AutoClient((Auto)o2[0], (Client)o2[1]);
			al.add(ac);
		}
		return al;
		
		//return autos;
	}
	
	public boolean insert(Auto a) {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
        session.save(a);
		session.getTransaction().commit();
		
		session.close();
		return true;
	}
	
	public boolean isExist(Auto a) {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();		
		List clients = session
			.createQuery("from dao2.Auto as a where a.nr = '" + a.getNr() + "' and a.idClient = " + a.getIdClient())
			.list();
		session.getTransaction().commit();
		session.close();		
		if(clients.size() > 0) {
			return true;
		} else {
			return false;
		}
	}	
}
