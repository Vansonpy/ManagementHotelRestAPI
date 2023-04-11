package com.example.reafult.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Contact")
public class Contact {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "subject")
	private String subject;
	
	@Column(name = "message")
	private String message;

	@Column(name = "email")
	@NotNull
	@Email
	private String email;

	@Column(name = "phone")
	private String phone;

	public Contact() {
		super();
	}

	public Contact(Integer id, String userName, String subject, String message, String email, String phone) {
		super();
		this.id = id;
		this.userName = userName;
		this.subject = subject;
		this.message = message;
		this.email = email;
		this.phone = phone;
	}

	public Contact(String userName, String subject, String message, String email, String phone) {
		super();
		this.userName = userName;
		this.subject = subject;
		this.message = message;
		this.email = email;
		this.phone = phone;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", userName=" + userName + ", subject=" + subject + ", message=" + message
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}
