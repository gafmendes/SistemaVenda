package br.com.sistemasvendas.dao;

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
	
}
