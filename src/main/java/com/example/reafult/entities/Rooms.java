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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rooms")
public class Rooms {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "room_name")
	@NotNull
	private String roomName;
	
	@Column(name = "type")
	@NotNull
	private String type;
	
	@Column(name = "price")
	@NotNull
	private Integer price;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
	private Set<Sales> sales;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
	private FileDB fileDB;
	
	public Rooms() {
		super();
	}

	public Rooms(Integer id, String roomName, String type, Integer price, Set<Sales> sales, FileDB fileDB) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.type = type;
		this.price = price;
		this.sales = sales;
		this.fileDB = fileDB;
	}

	public Rooms(String roomName, String type, Integer price) {
		super();
		this.roomName = roomName;
		this.type = type;
		this.price = price;
	}

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

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	public FileDB getFileDB() {
		return fileDB;
	}

	public void setFileDB(FileDB fileDB) {
		this.fileDB = fileDB;
	}

	@Override
	public String toString() {
		return "Rooms [id=" + id + ", roomName=" + roomName + ", type=" + type + ", price=" + price + ", sales=" + sales
				+ ", fileDB=" + fileDB + "]";
	}

}
