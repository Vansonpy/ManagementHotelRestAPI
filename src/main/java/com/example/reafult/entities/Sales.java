package com.example.reafult.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;


@Entity
@Table(name = "Sales")
@Check(constraints = "checkout_date>=checkin_date")
public class Sales {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "checkin_date")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date checkinDate;

	@Column(name = "checkout_date")
	@Temporal(TemporalType.DATE)
	private Date checkoutDate;

	@Column(name = "status")
	private Integer status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerid", referencedColumnName = "id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoomId", referencedColumnName = "id")
	private Rooms room;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.ALL)
	private Set<Services> services;
	
	public Sales() {
		super();
	}

	public Sales(Integer id, Date checkinDate, Date checkoutDate, Integer status, Customer customer, Rooms room,
			Set<Services> services) {
		super();
		this.id = id;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.status = status;
		this.customer = customer;
		this.room = room;
		this.services = services;
	}

	public Sales(Date checkinDate, Date checkoutDate, Integer status) {
		super();
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate + ", status="
				+ status + ", services=" + services + "]";
	}
	
}
