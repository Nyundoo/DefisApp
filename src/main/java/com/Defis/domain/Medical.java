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
	private String clientInfo;

	@Column(name = "medical_center", length = 45, nullable = false)
	private String medicalCenter;

	@Column(name = "medical_type", length = 45, nullable = false)
	private String medicalType;

	@Column(name = "amount_paid", length = 20, nullable = false)
	private double amountPaid;

	@Column(name = "application_date", length = 16, nullable = false)
	private Date applicationDate;

	private boolean active = false;

	@Column(name = "cert_no", length = 16, nullable = true)
	private String certNo;

	private boolean cert_status = false;

	@Column(length = 20, nullable = true)
	private String paid;

	@Column(name = "cert_application_date", length = 16, nullable = true)
	private Date certApplicationDate;

	@Column(name = "assign_to", length = 45, nullable = true)
	private String assignTo;

	@Column(name = "passport_no", length = 30, nullable = true)
	private String passportNo;

	private boolean pass_status = false;

	@Column(name = "pass_paid", length = 16, nullable = true)
	private String passPaid;

	@Column(name = "pass_application_date", length = 16, nullable = true)
	private Date passApplicationDate;

	@Column(name = "passAssignTo", length = 16, nullable = true)
	private String pass_assign_to;

	public Medical() {
	}

	public Medical(Integer id, Applicant applicant, String clientInfo, String medicalCenter, String medicalType,
			double amountPaid, Date applicationDate, boolean active, String certNo, boolean cert_status, String paid,
			Date certApplicationDate, String assignTo, String passportNo, boolean pass_status, String passPaid,
			Date passApplicationDate, String pass_assign_to) {
		super();
		this.id = id;
		this.applicant = applicant;
		this.clientInfo = clientInfo;
		this.medicalCenter = medicalCenter;
		this.medicalType = medicalType;
		this.amountPaid = amountPaid;
		this.applicationDate = applicationDate;
		this.active = active;
		this.certNo = certNo;
		this.cert_status = cert_status;
		this.paid = paid;
		this.certApplicationDate = certApplicationDate;
		this.assignTo = assignTo;
		this.passportNo = passportNo;
		this.pass_status = pass_status;
		this.passPaid = passPaid;
		this.passApplicationDate = passApplicationDate;
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

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getMedicalCenter() {
		return medicalCenter;
	}

	public void setMedicalCenter(String medicalCenter) {
		this.medicalCenter = medicalCenter;
	}

	public String getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(String medicalType) {
		this.medicalType = medicalType;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	public Date getCertApplicationDate() {
		return certApplicationDate;
	}

	public void setCertApplicationDate(Date certApplicationDate) {
		this.certApplicationDate = certApplicationDate;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public boolean isPass_status() {
		return pass_status;
	}

	public void setPass_status(boolean pass_status) {
		this.pass_status = pass_status;
	}

	public String getPassPaid() {
		return passPaid;
	}

	public void setPassPaid(String passPaid) {
		this.passPaid = passPaid;
	}

	public Date getPassApplicationDate() {
		return passApplicationDate;
	}

	public void setPassApplicationDate(Date passApplicationDate) {
		this.passApplicationDate = passApplicationDate;
	}

	public String getPass_assign_to() {
		return pass_assign_to;
	}

	public void setPass_assign_to(String pass_assign_to) {
		this.pass_assign_to = pass_assign_to;
	}

}
