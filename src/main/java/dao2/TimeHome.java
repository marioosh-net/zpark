package dao2;

// Generated 2009-11-28 22:17:34 by Hibernate Tools 3.2.5.Beta

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Time.
 * 
 * @see dao2.Time
 * @author Hibernate Tools
 */
public class TimeHome {

	private static final Log log = LogFactory.getLog(TimeHome.class);

	private final SessionFactory sessionFactory = hibernate.HibernateUtil
			.getSessionFactory();

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

	public void persist(Time transientInstance) {
		log.debug("persisting Time instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Time instance) {
		log.debug("attaching dirty Time instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Time instance) {
		log.debug("attaching clean Time instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Time persistentInstance) {
		log.debug("deleting Time instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Time merge(Time detachedInstance) {
		log.debug("merging Time instance");
		try {
			Time result = (Time) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Time findById(int id) {
		log.debug("getting Time instance with id: " + id);
		try {
			Time instance = (Time) sessionFactory.getCurrentSession().get(
					"dao2.Time", id);
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

	public List findByExample(Time instance) {
		log.debug("finding Time instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"dao2.Time").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findAll2() {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List times = session.createQuery("from dao2.Time").list();
		session.getTransaction().commit();
		session.close();
		return times;
	}
	
	public List<AutoTime> findAll() {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List times = session.createQuery("from Auto as a, Time as t where a.idAuto = t.idAuto").list();
		session.getTransaction().commit();
		session.close();
		
		ArrayList al = new ArrayList();
		for(Object o: times) {
			Object[] o2 = (Object[])o;
			AutoTime ac = new AutoTime((Auto)o2[0], (Time)o2[1]);
			al.add(ac);
		}
		return al;
		
		//return autos;
	}	

	// czy jest zaparkowane aktualnie auto a
	public boolean isExist(Auto a) {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List times = session.createQuery(
				"from dao2.Time as t where t.idAuto = " + a.getIdAuto()
						+ " and t.timeTo is not null").list();
		session.getTransaction().commit();
		session.close();
		if (times.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean insert(Time t) {
		org.hibernate.Session session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean park(Auto auto) {
		Time t = new Time();
		t.setIdAuto(auto.getIdAuto());
		t.setTimeFrom(new Date(new java.util.Date().getTime()));
		if (insert(t)) {
			return true;
		} else {
			return false;
		}
	}
}
