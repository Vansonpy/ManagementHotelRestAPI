package com.example.reafult.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Service")
public class Services {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name",unique = true)
	@NotNull
	private String name;
	
	@Column(name = "species")
	@NotNull
	private String species;
	
	@Column(name = "quantily")
	private Integer quantily;
	
	@Column(name = "price")
	@NotNull
	private Integer price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SaleId", referencedColumnName = "id")
	private Sales sale;

	public Services() {
		super();
	}

	public Services(Integer id, String name, String species, Integer quantily, Integer price, Sales sale) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.quantily = quantily;
		this.price = price;
		this.sale = sale;
	}

	public Services(String name, String species, Integer quantily, Integer price) {
		super();
		this.name = name;
		this.species = species;
		this.quantily = quantily;
		this.price = price;
	}

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

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", species=" + species + ", quantily=" + quantily + ", price="
				+ price + ", sale=" + sale + "]";
	}

}
