package br.com.sistemasvendas.util;

import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		}
		return sessionFactory;
	}
	
}
