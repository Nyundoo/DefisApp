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

@Entity(name = "Birth")
@Table(name = "birth")
public class Birth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "cert_no", length = 16, nullable = false)
	private String cert_no;

	@Column(length = 20, nullable = true)
	private String paid;

	@Column(name = "cert_application_date", length = 16, nullable = false)
	private Date cert_application_date;

	@Column(name = "cert_reception_date", length = 16, nullable = false)
	private Date cert_reception_date;

	@ManyToOne
	@JoinColumn(name = "user_id2")
	private User user2;

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

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public Date getCert_application_date() {
		return cert_application_date;
	}

	public void setCert_application_date(Date cert_application_date) {
		this.cert_application_date = cert_application_date;
	}

	public Date getCert_reception_date() {
		return cert_reception_date;
	}

	public void setCert_reception_date(Date cert_reception_date) {
		this.cert_reception_date = cert_reception_date;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
