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

@Entity(name = "Visa")
@Table(name = "visa")
public class Visa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "type_of_visa", length = 64, nullable = false)
	private String type_of_visa;

	@Column(name = "visa_apply_date", length = 16, nullable = false)
	private Date visa_apply_date;

	@Column(name = "visa_reception_date", length = 16, nullable = true)
	private Date visa_reception_date;

	@ManyToOne
	@JoinColumn(name = "user_id4")
	private User user4;

	private boolean status = false;

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

	public String getType_of_visa() {
		return type_of_visa;
	}

	public void setType_of_visa(String type_of_visa) {
		this.type_of_visa = type_of_visa;
	}

	public Date getVisa_apply_date() {
		return visa_apply_date;
	}

	public void setVisa_apply_date(Date visa_apply_date) {
		this.visa_apply_date = visa_apply_date;
	}

	public Date getVisa_reception_date() {
		return visa_reception_date;
	}

	public void setVisa_reception_date(Date visa_reception_date) {
		this.visa_reception_date = visa_reception_date;
	}

	public User getUser4() {
		return user4;
	}

	public void setUser4(User user4) {
		this.user4 = user4;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
