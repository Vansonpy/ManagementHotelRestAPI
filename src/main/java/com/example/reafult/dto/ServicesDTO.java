package com.example.reafult.dto;

public class ServicesDTO {
	private Integer id;
	private String name;
	private String species;
	private Integer quantily;
	private Integer price;
	private Integer saleId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public Integer getQuantily() {
		return quantily;
	}
	public void setQuantily(Integer quantily) {
		this.quantily = quantily;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	@Override
	public String toString() {
		return "ServiceDTO [id=" + id + ", name=" + name + ", species=" + species + ", quantily=" + quantily
				+ ", price=" + price + ", saleId=" + saleId + "]";
	}
	
	
}
