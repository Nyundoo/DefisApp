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

	@Column(name = "re_first_name", length = 45, nullable = false)
	private String reFirstName;

	@Column(name = "re_last_name", length = 45, nullable = false)
	private String reLastName;

	@Column(name = "re_contact", length = 16, nullable = false)
	private String reContact;

	@Column(name = "re_national_id", length = 16, nullable = false)
	private String reNationalId;

	@Column(name = "re_relationship", length = 15, nullable = false)
	private String reRelationship;

	@Column(name = "re_current_residence", length = 45, nullable = false)
	private String reCurrentResidence;

	@Column(name = "re_huduma_no", length = 45, nullable = false)
	private String reHudumaNo;

	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	public ApplicantDetails() {
	}

	public ApplicantDetails(Integer id, String reFirstName, String reLastName, String reContact, String reNationalId,
			String reRelationship, String reCurrentResidence, String reHudumaNo, Applicant applicant) {
		super();
		this.id = id;
		this.reFirstName = reFirstName;
		this.reLastName = reLastName;
		this.reContact = reContact;
		this.reNationalId = reNationalId;
		this.reRelationship = reRelationship;
		this.reCurrentResidence = reCurrentResidence;
		this.reHudumaNo = reHudumaNo;
		this.applicant = applicant;
	}

	public ApplicantDetails(String reFirstName, String reLastName, String reContact, String reNationalId,
			String reRelationship, String reCurrentResidence, String reHudumaNo, Applicant applicant) {
		this.reFirstName = reFirstName;
		this.reLastName = reLastName;
		this.reContact = reContact;
		this.reNationalId = reNationalId;
		this.reRelationship = reRelationship;
		this.reCurrentResidence = reCurrentResidence;
		this.reHudumaNo = reHudumaNo;
		this.applicant = applicant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReFirstName() {
		return reFirstName;
	}

	public void setReFirstName(String reFirstName) {
		this.reFirstName = reFirstName;
	}

	public String getReLastName() {
		return reLastName;
	}

	public void setReLastName(String reLastName) {
		this.reLastName = reLastName;
	}

	public String getReContact() {
		return reContact;
	}

	public void setReContact(String reContact) {
		this.reContact = reContact;
	}

	public String getReNationalId() {
		return reNationalId;
	}

	public void setReNationalId(String reNationalId) {
		this.reNationalId = reNationalId;
	}

	public String getReRelationship() {
		return reRelationship;
	}

	public void setReRelationship(String reRelationship) {
		this.reRelationship = reRelationship;
	}

	public String getReCurrentResidence() {
		return reCurrentResidence;
	}

	public void setReCurrentResidence(String reCurrentResidence) {
		this.reCurrentResidence = reCurrentResidence;
	}

	public String getReHudumaNo() {
		return reHudumaNo;
	}

	public void setReHudumaNo(String reHudumaNo) {
		this.reHudumaNo = reHudumaNo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	@Override
	public String toString() {
		return reFirstName + " " + reLastName + " " + reRelationship + " " + reContact;
	}

}
