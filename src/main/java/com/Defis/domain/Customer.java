package com.Defis.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String identification;
	private String age;
	private String name;
	private String job_country;
	private String job;
	private String email;
	private String county;
	private String ward;
	private String village_name;
	private Integer huduma_no;
	private String gender;
	private String chief_name;
	private String contact;
	
	@Transient
	private MultipartFile itemImage;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerDetails> details = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification_no) {
		this.identification = identification_no;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob_country() {
		return job_country;
	}

	public void setJob_country(String job_country) {
		this.job_country = job_country;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
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

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public Integer getHuduma_no() {
		return huduma_no;
	}

	public void setHuduma_no(Integer huduma_no) {
		this.huduma_no = huduma_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getChief_name() {
		return chief_name;
	}

	public void setChief_name(String chief_name) {
		this.chief_name = chief_name;
	}	

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void addDetail(String cname, String ccontact, String cnational_id, String crelationship, String cemail, String ccounty, String cward, String ccurrent_residence) {
		this.details.add(new CustomerDetails(cname, ccontact, cnational_id, crelationship, cemail, ccounty, cward, ccurrent_residence, this));
	}

	public List<CustomerDetails> getDetails() {
		return details;
	}

	public void setDetails(List<CustomerDetails> details) {
		this.details = details;
	}
	
	public void setDetail(Integer id, String cname, String ccontact, String cnational_id, String crelationship, String cemail, String ccounty, String cward, String ccurrent_residence) {
		this.details.add(new CustomerDetails(id, cname, ccontact, cnational_id, crelationship, cemail, ccounty, cward, ccurrent_residence, this));
	}

	public MultipartFile getItemImage() {
		return itemImage;
	}

	public void setItemImage(MultipartFile itemImage) {
		this.itemImage = itemImage;
	}
	
	@Transient
	public String getMainImagePath() {
		if(id == null) return null;
		
		return "/customer-images/customer/" + id +".jpg";
	}
	
}
