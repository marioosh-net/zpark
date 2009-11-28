package dao;
import hibernate.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class User2DAO {

	private SessionFactory sessionFactory;
	private Session session;

	public User2DAO() {
		//read hibernate.cfg.xml and prepare hibernate for use
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public boolean checkUser(User2 user) {
		session = sessionFactory.openSession();
		session.beginTransaction();
        List users = session.createQuery("from dao.User2 where login = '"+user.getLogin()+"' and pass = '"+user.getPass()+"'").list();
		session.getTransaction().commit();
		session.close();        
        if(users.isEmpty()) {
        	return false;
        } else {
        	return true;
        }
	}

	public List findAll() {
		session = sessionFactory.openSession();
		session.beginTransaction();
        List users = session.createQuery("from dao.User2").list();
		session.getTransaction().commit();
		session.close();
		return users;
	}

	public boolean delete(User2 user) {
		return false;
	}

	public boolean insert(User2 user) {
		// with transaction
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user); // insert
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertNoTransaction(User2 user) {
		try {
			session = sessionFactory.openSession();
			session.save(user); // insert
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// actual contact insertion will happen at this step
			session.flush();
			session.close();
		}
		return true;
	}

	public boolean update(User2 user) {
		return false;
	}
}