package br.com.sistemasvendas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Nome deve ser informado")
	private String name;

	@CPF
	@CNPJ
	private String cpfOrCnpj;

	private List<Product> product;

	public User() {

	}

	public User(String name, String cpfOrCnpj) {
		this.name = name;
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public User(int id, String name, String cpfOrCnpj) {
		this.id = id;
		this.name = name;
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public User(int id, String name, String cpfOrCnpj, List<Product> product) {
		this.id = id;
		this.name = name;
		this.cpfOrCnpj = cpfOrCnpj;
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
