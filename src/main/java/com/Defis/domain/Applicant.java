package com.Defis.domain;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Applicant")
@Table(name = "applicant")
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45, nullable = false, unique = true)
	private String identification;

	@Column(length = 3, nullable = false, unique = false)
	private Integer age;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(length = 200, nullable = false, unique = false)
	private String county;

	@Column(length = 45, nullable = true, unique = false)
	private String ward;

	@Column(name = "village_name", length = 45, nullable = true, unique = false)
	private String villageName;

	@Column(name = "huduma_no", length = 45, nullable = true, unique = false)
	private Integer hudumaNo;

	@Column(length = 7, nullable = false, unique = false)
	private String gender;

	@Column(name = "chief_name", length = 45, nullable = true, unique = false)
	private String chiefName;

	@Column(length = 45, nullable = false, unique = false)
	private String contact;

	@Column(length = 64)
	private String photos;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "applicant")
	private Medical medical;

	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
	private List<ApplicantDetails> details = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillagName(String villagName) {
		this.villageName = villagName;
	}

	public Integer getHudumaNo() {
		return hudumaNo;
	}

	public void setHudumaNo(Integer hudumaNo) {
		this.hudumaNo = hudumaNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Medical getMedical() {
		return medical;
	}

	public void setMedical(Medical medical) {
		this.medical = medical;
	}

	public List<ApplicantDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ApplicantDetails> details) {
		this.details = details;
	}

	public void setDetail(Integer id, String reFirstName, String reLastName, String reContact, String reNationalId,
			String reRelationship, String reCurrentResidence, String reHudumaNo) {
		this.details.add(new ApplicantDetails(id, reFirstName, reLastName, reContact, reNationalId, reRelationship,
				reCurrentResidence, reHudumaNo, this));
	}

	@Transient
	public String getPhotosImagePath() {
		if (id == null || photos == null)
			return "/images/default-user.png";

		return "/applicant-photos/" + this.id + "/" + this.photos;
	}
	
	@Transient
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
