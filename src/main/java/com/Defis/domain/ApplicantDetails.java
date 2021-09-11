package com.Defis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "ApplicantDetails")
@Table(name = "applicant_details")
public class ApplicantDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "re_first_name", length = 45, nullable = true)
	private String cname;

	@Column(name = "re_contact", length = 16, nullable = true)
	private Integer ccontact;

	@Column(name = "re_national_id", length = 16, nullable = true)
	private Integer cnational_id;

	@Column(name = "re_relationship", length = 15, nullable = true)
	private String crelationship;

	@Column(name = "re_current_residence", length = 45, nullable = true)
	private String ccurrent_residence;

	@Column(name = "re_huduma_no", length = 45, nullable = true)
	private Integer chuduma_no;

	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	

	

	public ApplicantDetails(Integer id, String cname, Integer ccontact, Integer cnational_id, String crelationship, String ccurrent_residence, Applicant applicant) {
		super();
		this.id = id;
		this.cname = cname;
		this.ccontact = ccontact;
		this.cnational_id = cnational_id;
		this.crelationship = crelationship;
		this.ccurrent_residence = ccurrent_residence;
		this.applicant = applicant;
	}

	public ApplicantDetails() {
	}


	public ApplicantDetails(String cname, Integer ccontact, Integer cnational_id, String crelationship, String ccurrent_residence, Applicant applicant) {		
		this.cname = cname;
		this.ccontact = ccontact;
		this.cnational_id = cnational_id;
		this.crelationship = crelationship;
		this.ccurrent_residence = ccurrent_residence;
		this.applicant = applicant;
	}	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCcontact() {
		return ccontact;
	}

	public void setCcontact(Integer ccontact) {
		this.ccontact = ccontact;
	}

	public Integer getCnational_id() {
		return cnational_id;
	}

	public void setCnational_id(Integer cnational_id) {
		this.cnational_id = cnational_id;
	}

	public String getCrelationship() {
		return crelationship;
	}

	public void setCrelationship(String crelationship) {
		this.crelationship = crelationship;
	}

	public String getCcurrent_residence() {
		return ccurrent_residence;
	}

	public void setCcurrent_residence(String ccurrent_residence) {
		this.ccurrent_residence = ccurrent_residence;
	}

	public Integer getChuduma_no() {
		return chuduma_no;
	}

	public void setChuduma_no(Integer chuduma_no) {
		this.chuduma_no = chuduma_no;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	@Override
	public String toString() {
		return cname + " " + crelationship + " " + ccontact;
	}

}
