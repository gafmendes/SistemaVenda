package br.com.sistemasvendas.model;

import javax.persistence.Entity;

@Entity
public class Product {

	private int id;

	private String descricao;

	private Double preco;

	private User user;

	public Product() {
		super();
	}

	public Product(int id, String descricao, Double preco, User user) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
