package com.example.reafult.dto;

import java.util.List;

public class UsersDTO {
	
	private Integer userId;
	private String fullName;
	private String userName;
	private String email;
	private String phone;
	private Byte gender;
	private String role;
	private List<SalesDTO> salesDTO;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Integer getId() {
		return userId;
	}

	public void setId(Integer userId) {
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

	public List<SalesDTO> getSalesDTO() {
		return salesDTO;
	}

	public void setSalesDTO(List<SalesDTO> salesDTO) {
		this.salesDTO = salesDTO;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email
				+ ", phone=" + phone + ", gender=" + gender + ", role=" + role + ", salesDTO=" + salesDTO + "]";
	}

	
}
