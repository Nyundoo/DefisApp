package com.Defis.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Medical")
@Table(name = "medical")
public class Medical {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "client_info", columnDefinition = "text", nullable = true)
	private String client_info;

	@Column(name = "medical_center", length = 45, nullable = true)
	private String medical_center;

	@Column(name = "medical_type", length = 45, nullable = true)
	private String medical_type;

	@Column(name = "amount_paid", length = 20, nullable = true)
	private double amount_paid;

	@Column(name = "application_date", length = 16, nullable = true)
	private Date application_date;

	@Column(name = "result_date", length = 16, nullable = true)
	private Date result_date;

	private boolean status = false;

	@ManyToOne
	@JoinColumn(name = "user_id1")
	private User user1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
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

	public Date getResult_date() {
		return result_date;
	}

	public void setResult_date(Date result_date) {
		this.result_date = result_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

}
