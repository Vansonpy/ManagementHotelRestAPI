package com.example.reafult.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "email")
	@NotNull
	@Email
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Sales> sales;
	
	public Customer() {
		super();
	}

	public Customer(Integer id, String userName, String email, Set<Sales> sales) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.sales = sales;
	}

	public Customer(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", email=" + email + ", sales=" + sales + "]";
	}

}
