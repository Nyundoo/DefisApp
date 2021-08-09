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

	@ManyToOne
	@JoinColumn(name = "user_id1")
	private User user1;

	@Column(name = "passport_no", length = 30, nullable = true)
	private String passport_no;

	private boolean pass_status = false;

	@Column(name = "pass_paid", length = 20, nullable = true)
	private String pass_paid;

	@Column(name = "pass_application_date", length = 16, nullable = true)
	private Date pass_application_date;

	@ManyToOne
	@JoinColumn(name = "user_id2")
	private User user2;

	@Column(name = "type_of_visa", length = 64, nullable = true)
	private String type_of_visa;

	@Column(name = "visa_apply_date", length = 16, nullable = true)
	private Date visa_apply_date;

	private boolean status = false;

	@Column(name = "agent_or_company", length = 64, nullable = true)
	private String agent_or_company;

	public Medical() {
	}

	public Medical(Integer id, Applicant applicant, String client_info, String medical_center, String medical_type,
			double amount_paid, Date application_date, boolean active, String cert_no, boolean cert_status, String paid,
			Date cert_application_date, User user1, String passport_no, boolean pass_status, String pass_paid,
			Date pass_application_date, User user2, String type_of_visa, Date visa_apply_date, boolean status,
			String agent_or_company) {
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
		this.user1 = user1;
		this.passport_no = passport_no;
		this.pass_status = pass_status;
		this.pass_paid = pass_paid;
		this.pass_application_date = pass_application_date;
		this.user2 = user2;
		this.type_of_visa = type_of_visa;
		this.visa_apply_date = visa_apply_date;
		this.status = status;
		this.agent_or_company = agent_or_company;
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

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
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

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAgent_or_company() {
		return agent_or_company;
	}

	public void setAgent_or_company(String agent_or_company) {
		this.agent_or_company = agent_or_company;
	}

}
