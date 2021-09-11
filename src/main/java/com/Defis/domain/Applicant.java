package com.Defis.domain;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(length = 8, nullable = true, unique = false)
	private Integer identification;

	@Column(length = 3, nullable = true, unique = false)
	private Integer age;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "applicants_jobs", joinColumns = @JoinColumn(name = "applicant_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
	private Set<Jobs> jobs = new HashSet<>();

	@Column(length = 128, nullable = true, unique = true)
	private String email;

	@Column(length = 200, nullable = true, unique = false)
	private String county;

	@Column(length = 45, nullable = true, unique = false)
	private String ward;

	@Column(name = "village_name", length = 45, nullable = true, unique = false)
	private String villageName;

	@Column(name = "huduma_no", length = 45, nullable = true, unique = false)
	private Integer hudumaNo;

	@Column(length = 7, nullable = true, unique = false)
	private String gender;

	@Column(name = "chief_name", length = 45, nullable = true, unique = false)
	private String chiefName;

	@Column(length = 45, nullable = true, unique = false)
	private String contact;

	@Column(length = 64)
	private String photos;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "applicant")
	private Medical medical;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "applicant")
	private Medical tickets;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "applicant")
	private Medical training;

	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
	private List<ApplicantDetails> details = new ArrayList<>();
	
	

	public Applicant() {
	}

	public Applicant( String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdentification() {
		return identification;
	}

	public void setIdentification(Integer identification) {
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

	public Set<Jobs> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Jobs> jobs) {
		this.jobs = jobs;
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
	
	public Medical getTickets() {
		return tickets;
	}

	public void setTickets(Medical tickets) {
		this.tickets = tickets;
	}

	public Medical getTraining() {
		return training;
	}

	public void setTraining(Medical training) {
		this.training = training;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public void addDetail(String cname, Integer ccontact, Integer cnational_id, String crelationship, String ccurrent_residence) {
		this.details.add(new ApplicantDetails(cname, ccontact, cnational_id, crelationship, ccurrent_residence, this));
	}

	public void setDetail(Integer id, String cname, Integer ccontact, Integer cnational_id, String crelationship, String ccurrent_residence) {
		this.details.add(new ApplicantDetails(id, cname, ccontact, cnational_id, crelationship, ccurrent_residence, this));
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
