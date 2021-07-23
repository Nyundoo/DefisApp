package com.Defis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Agent")
@Table(name = "agent")
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String id_no;
	private String name;
	
	private String phone_number1;
	private String phone_number2;
	private String current_residence;
	private String email;
	private String county;
	private String ward;
	private String village_name;
	
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	public Agent() {
	}
	
	public Agent(Integer id) {
		this.id = id;
	}
	
	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public Agent(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
			
	public String getPhone_number1() {
		return phone_number1;
	}

	public void setPhone_number1(String phone_number1) {
		this.phone_number1 = phone_number1;
	}
	
	public String getPhone_number2() {
		return phone_number2;
	}

	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getCurrent_residence() {
		return current_residence;
	}

	public void setCurrent_residence(String current_residence) {
		this.current_residence = current_residence;
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

	
	
	
}
