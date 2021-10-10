package br.com.sistemasvendas.dao;

import java.util.List;

import javax.print.attribute.PrintServiceAttribute;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemasvendas.model.User;
import br.com.sistemasvendas.util.HibernateUtil;

public class UserDAO {

	private static UserDAO instance;

	private UserDAO() {

	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public void save(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// salva o objeto
			session.save(user);
			// commita a transacao
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void update(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// altera o objeto
			session.update(user);
			// commita a transacao
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comeca a transacao com o banco da dados
			transaction = session.beginTransaction();
			// deleta o usuario
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("Usuario deletado");
			}
			// commita a transacao
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void get(int id) {
		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// pega um objeto do tipo usuario
			user = session.get(User.class, id);
			// commita a transacao
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> get() {
		Transaction transaction = null;
		List<User> users = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// pega um objeto do tipo lista de usuario
			users = session.createQuery("from User").getResultList();
			// comita a transacao
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return users;
	}

}
