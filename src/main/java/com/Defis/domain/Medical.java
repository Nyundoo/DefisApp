package com.Defis.domain;

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
public class Medical {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "client_info", columnDefinition = "text")
	private String client_info;

	@Column(name = "medical_center", length = 45, nullable = false)
	private String medical_center;

	@Column(name = "medical_type", length = 45, nullable = false)
	private String medical_type;

	@Column(name = "amount_paid", length = 20, nullable = false)
	private double amount_paid;

	@Column(name = "application_date", length = 16, nullable = false)
	private Date application_date;

	private boolean active = false;

	@Column(name = "cert_no", length = 16, nullable = true)
	private String cert_no;

	private boolean cert_status = false;

	@Column(length = 20, nullable = true)
	private String paid;

	@Column(name = "cert_application_date", length = 16, nullable = true)
	private Date cert_application_date;

	@Column(name = "assign_to", length = 45, nullable = true)
	private String assign_to;

	@Column(name = "passport_no", length = 30, nullable = true)
	private String passport_no;

	private boolean pass_status = false;

	@Column(name = "pass_paid", length = 16, nullable = true)
	private String pass_paid;

	@Column(name = "pass_application_date", length = 16, nullable = true)
	private Date pass_application_date;

	@Column(name = "passAssignTo", length = 16, nullable = true)
	private String pass_assign_to;

	public Medical() {
	}

	public Medical(Integer id, Applicant applicant, String client_info, String medical_center, String medical_type,
			double amount_paid, Date application_date, boolean active, String cert_no, boolean cert_status, String paid,
			Date cert_application_date, String assign_to, String passport_no, boolean pass_status, String pass_paid,
			Date pass_application_date, String pass_assign_to) {
		super();
		this.id = id;
		this.applicant = applicant;
		this.client_info = client_info;
		this.medical_center = medical_center;
		this.medical_type = medical_type;
		this.amount_paid = amount_paid;
		this.application_date = application_date;
		this.active = active;
		this.cert_no = cert_no;
		this.cert_status = cert_status;
		this.paid = paid;
		this.cert_application_date = cert_application_date;
		this.assign_to = assign_to;
		this.passport_no = passport_no;
		this.pass_status = pass_status;
		this.pass_paid = pass_paid;
		this.pass_application_date = pass_application_date;
		this.pass_assign_to = pass_assign_to;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public boolean isCert_status() {
		return cert_status;
	}

	public void setCert_status(boolean cert_status) {
		this.cert_status = cert_status;
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

	public String getAssign_to() {
		return assign_to;
	}

	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
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

	public String getPass_assign_to() {
		return pass_assign_to;
	}

	public void setPass_assign_to(String pass_assign_to) {
		this.pass_assign_to = pass_assign_to;
	}

}
