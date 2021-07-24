package com.Defis.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Job")
@Table(name = "job")
public class Job {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String country;
	
	private String job_title;
	private double payment;
	
	@Column(columnDefinition="text")
	private String job_description;

	private String qualification;
	private Date interview_date;
	private Integer no_vacancy;
	private Date date_posted;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
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
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Date getInterview_date() {
		return interview_date;
	}
	public void setInterview_date(Date interview_date) {
		this.interview_date = interview_date;
	}
	public Integer getNo_vacancy() {
		return no_vacancy;
	}
	public void setNo_vacancy(Integer no_vacancy) {
		this.no_vacancy = no_vacancy;
	}
	public Date getDate_posted() {
		return date_posted;
	}
	public void setDate_posted(Date date_posted) {
		this.date_posted = date_posted;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
	
}
