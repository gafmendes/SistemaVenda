package br.com.sistemasvendas.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class User {

	private int id;

	private String name;

	private String cpf;

	private String cnpj;

	private List<Product> product;

	public User() {

	}

	public User(int id, String name, String cpf, String cnpj, List<Product> product) {

		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
