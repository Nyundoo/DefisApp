package com.Defis.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Jobs")
@Table(name = "jobs")
public class Jobs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 40, nullable = false)
	private String country;

	@Column(name = "job_title", length = 45, nullable = false)
	private String jobTitle;
	
	private boolean pay = false;

	@Column(length = 10, nullable = false)
	private double payment;

	@Column(name = "job_description", columnDefinition = "text")
	private String jobDescription;

	@Column(length = 250, nullable = false)
	private String qualification;

	@Column(name = "interview_date", length = 10, nullable = false)
	private Date interviewDate;

	@Column(name = "no_vacancy", length = 45, nullable = false)
	private Integer noVacancy;

	@Column(name = "date_posted", length = 10, nullable = false)
	private Date datePosted;	
	
	public Jobs() {
	}

	public Jobs(Integer id) {
		this.id = id;
	}

	public Jobs(Integer id, String country, String jobTitle, boolean pay, double payment, String jobDescription,
			String qualification, Date interviewDate, Integer noVacancy, Date datePosted) {
		this.id = id;
		this.country = country;
		this.jobTitle = jobTitle;
		this.pay = pay;
		this.payment = payment;
		this.jobDescription = jobDescription;
		this.qualification = qualification;
		this.interviewDate = interviewDate;
		this.noVacancy = noVacancy;
		this.datePosted = datePosted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public boolean isPay() {
		return pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public Integer getNoVacancy() {
		return noVacancy;
	}

	public void setNoVacancy(Integer noVacancy) {
		this.noVacancy = noVacancy;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jobs other = (Jobs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.jobTitle;
	}
}
