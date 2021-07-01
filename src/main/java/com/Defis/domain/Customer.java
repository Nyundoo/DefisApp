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

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private Integer identification_no;
	private Integer age;
	private String name;
	private String email;
	private String county;
	private String ward;
	private String village_name;
	private Integer huduma_no;
	private String gender;
	private String chief_name;
	private String chief_contact;
	
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

	public Integer getIdentification_no() {
		return identification_no;
	}

	public void setIdentification_no(Integer identification_no) {
		this.identification_no = identification_no;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getChief_contact() {
		return chief_contact;
	}

	public void setChief_contact(String chief_contact) {
		this.chief_contact = chief_contact;
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
	
}
