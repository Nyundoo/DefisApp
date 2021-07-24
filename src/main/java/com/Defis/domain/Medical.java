package com.Defis.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Medical")
@Table(name = "medical")
public class Medical implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

	@Column(columnDefinition="text")
	private String client_info;
	private String medical_center;
	private String medical_type;
	private double amount_paid;
	private Date application_date;
	private boolean active=true;
	
	public Medical() {

    }
	
	public Medical(Integer id, Customer customer, String client_info, String medical_center, String medical_type,
			double amount_paid, Date application_date, boolean active) {
		super();
		this.id = id;
		this.customer = customer;
		client_info = client_info;
		this.medical_center = medical_center;
		this.medical_type = medical_type;
		this.amount_paid = amount_paid;
		this.application_date = application_date;
		this.active = active;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getClient_info() {
		return client_info;
	}

	public void setClient_info(String client_info) {
		this.client_info = client_info;
	}

	public String getMedical_center() {
		return medical_center;
	}
	public void setMedical_center(String medical_center) {
		this.medical_center = medical_center;
	}
	public String getMedical_type() {
		return medical_type;
	}
	public void setMedical_type(String medical_type) {
		this.medical_type = medical_type;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public Date getApplication_date() {
		return application_date;
	}
	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

		

}
