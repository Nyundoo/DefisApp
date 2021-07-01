package com.Defis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "CustomerDetails")
@Table(name = "customer_details")
public class CustomerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45, nullable = false)
	private String cname;
	
	@Column(length = 45, nullable = false, unique = false)
	private String ccontact;
	
	@Column(length = 45, nullable = false, unique = false)
	private String cnational_id;
	
	@Column(length = 45, nullable = false)
	private String crelationship;
	
	private String cemail;
	
	private String ccounty;
	
	private String cward;
	
	private String ccurrent_residence;
	
	@Column(length = 45, nullable = true)
	private String chuduma_no;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
	
	
	public CustomerDetails(Integer id, String cname, String ccontact, String cnational_id, String crelationship, String cemail, String ccounty, String cward, String ccurrent_residence, Customer customer) {
		super();
		this.id = id;
		this.cname = cname;
		this.ccontact = ccontact;
		this.cnational_id = cnational_id;
		this.crelationship = crelationship;
		this.cemail = cemail;
		this.ccounty = ccounty;
		this.cward = cward;
		this.ccurrent_residence = ccurrent_residence;
		this.customer = customer;
	}

	public CustomerDetails() {
		
	}
	
	public CustomerDetails(String cname, String ccontact, String cnational_id, String crelationship, String cemail, String ccounty, String cward, String ccurrent_residence, Customer customer) {		
		this.cname = cname;
		this.ccontact = ccontact;
		this.cnational_id = cnational_id;
		this.crelationship = crelationship;
		this.cemail = cemail;
		this.ccounty = ccounty;
		this.cward = cward;
		this.ccurrent_residence = ccurrent_residence;
		this.customer = customer;
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

	public String getCcontact() {
		return ccontact;
	}

	public void setCcontact(String ccontact) {
		this.ccontact = ccontact;
	}

	public String getCnational_id() {
		return cnational_id;
	}

	public void setCnational_id(String cnational_id) {
		this.cnational_id = cnational_id;
	}

	public String getCrelationship() {
		return crelationship;
	}

	public void setCrelationship(String crelationship) {
		this.crelationship = crelationship;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCcounty() {
		return ccounty;
	}

	public void setCcounty(String ccounty) {
		this.ccounty = ccounty;
	}

	public String getCward() {
		return cward;
	}

	public void setCward(String cward) {
		this.cward = cward;
	}

	public String getCcurrent_residence() {
		return ccurrent_residence;
	}

	public void setCcurrent_residence(String ccurrent_residence) {
		this.ccurrent_residence = ccurrent_residence;
	}

	public String getChuduma_no() {
		return chuduma_no;
	}

	public void setChuduma_no(String chuduma_no) {
		this.chuduma_no = chuduma_no;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return  cname + " " + crelationship + " " + ccontact;
	}
	
	
}
