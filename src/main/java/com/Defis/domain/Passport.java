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

@Entity(name = "Passport")
@Table(name = "passport")
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "passport_no", length = 30, nullable = true)
	private String passport_no;

	private boolean pass_status = false;

	@Column(name = "pass_paid", length = 20, nullable = true)
	private String pass_paid;

	@Column(name = "pass_application_date", length = 16, nullable = true)
	private Date pass_application_date;

	@Column(name = "pass_reception_date", length = 16, nullable = true)
	private Date pass_reception_date;
	
	@Column(length = 64)
	private String photos;

	@ManyToOne
	@JoinColumn(name = "user_id3")
	private User user3;

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

	public String getPassport_no() {
		return passport_no;
	}

	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}

	public boolean isPass_status() {
		return pass_status;
	}

	public void setPass_status(boolean pass_status) {
		this.pass_status = pass_status;
	}

	public String getPass_paid() {
		return pass_paid;
	}

	public void setPass_paid(String pass_paid) {
		this.pass_paid = pass_paid;
	}

	public Date getPass_application_date() {
		return pass_application_date;
	}

	public void setPass_application_date(Date pass_application_date) {
		this.pass_application_date = pass_application_date;
	}

	public Date getPass_reception_date() {
		return pass_reception_date;
	}

	public void setPass_reception_date(Date pass_reception_date) {
		this.pass_reception_date = pass_reception_date;
	}

	public User getUser3() {
		return user3;
	}

	public void setUser3(User user3) {
		this.user3 = user3;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

}
