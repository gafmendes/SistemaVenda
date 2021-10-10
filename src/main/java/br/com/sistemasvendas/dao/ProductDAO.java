package br.com.sistemasvendas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistemasvendas.model.Product;
import br.com.sistemasvendas.util.HibernateUtil;

public class ProductDAO {

	private static ProductDAO instance;
	
	private ProductDAO() {
		
	}
	
	public static ProductDAO getInstance() {
		if (instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	
	public List<Product> getByUserId(int userId){
		Transaction transaction = null;
		List<Product> products = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// lista os produtos vinculados ao id
			products = session.createQuery("FROM Product WHERE user_id = :user_id")
					.setParameter("user_id", userId)
					.getResultList();
			// commita a transacao
			transaction.commit();
					
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return products;
	}
	
	public void save(Product product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// salva o objeto
			session.save(product);
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// deleta o produto
			Product product = session.get(Product.class, id);
			if(product != null) {
				session.delete(product);
				System.out.println("Produto deletado");
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
	
	public Product get(int id) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			product = session.get(Product.class, id);
			// commita a transacao
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// caso haja erro rollback
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return product;
	}
	
	public void update(Product product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			// comeca a transacao com o banco de dados
			transaction = session.beginTransaction();
			// atualiza o objeto produto
			session.update(product);
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
}
