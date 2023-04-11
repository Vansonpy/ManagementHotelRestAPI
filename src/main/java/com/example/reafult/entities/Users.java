package com.example.reafult.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "full_name")
	@NotNull
	private String fullName;

	@Column(name = "user_name", unique = true)
	@NotNull
	@NotBlank
	@Length(min = 5, max = 10)
	private String userName;

	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "email", unique = true)
	@NotNull
	@Email
	private String email;

	@Column(name = "phone")
	@NotNull
	private String phone;

	@Column(name = "gender")
	private Byte gender;

	@Column(name = "role")
	private String role;
	
	@Column(name = "ENABLED")
	private Boolean enabled;

	public Users() {
		super();
	}

	public Users(Integer userId, String fullName, String userName, String password, String email, String phone,
			Byte gender, String role, Boolean enabled) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.role = role;
		this.enabled = enabled;
	}

	public Users(String fullName, String userName, String password, String email, String phone, Byte gender,
			String role, Boolean enabled) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.role = role;
		this.enabled = enabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
