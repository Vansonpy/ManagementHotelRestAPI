package com.example.reafult.dto;

import java.util.Date;
import java.util.List;

public class SalesDTO {
	
	private Integer id;
	private String roomName;
	private Date checkinDate;
	private Date checkoutDate;
	private Integer status;
	private String userName;
	private Integer price;
	private String type;
	private List<ServicesDTO> serviceDTO;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ServicesDTO> getServiceDTO() {
		return serviceDTO;
	}
	public void setServiceDTO(List<ServicesDTO> serviceDTO) {
		this.serviceDTO = serviceDTO;
	}
	@Override
	public String toString() {
		return "SalesDTO [id=" + id + ", roomName=" + roomName + ", checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", status=" + status + ", userName=" + userName + ", price=" + price + ", type=" + type
				+ ", serviceDTO=" + serviceDTO + "]";
	}
	
	
}
