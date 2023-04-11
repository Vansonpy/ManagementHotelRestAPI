package com.example.reafult.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RoomsDTO {
	private Integer id;
	private String roomName;
	private String type;
	private Integer price;
	private List<SalesDTO> salesDTO;
	private String userName;
	private String urlImage;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<SalesDTO> getSalesDTO() {
		return salesDTO;
	}

	public void setSalesDTO(List<SalesDTO> salesDTO) {
		this.salesDTO = salesDTO;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

}
