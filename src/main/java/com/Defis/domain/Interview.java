package com.Defis.domain;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Interview")
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "start_time", length = 16, nullable = false)
	private Date startTime;

	@Column(name = "type_of_interview", length = 240, nullable = false)
	private String typeOfInterview;

	private boolean i_status = false;

	@ManyToOne
	@JoinColumn(name = "user_id6")
	private User user6;

	public Interview() {
	}

	public Interview(Integer id, Applicant applicant, Date startTime, String typeOfInterview, boolean i_status,
			User user6) {
		this.id = id;
		this.applicant = applicant;
		this.startTime = startTime;
		this.typeOfInterview = typeOfInterview;
		this.i_status = i_status;
		this.user6 = user6;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getTypeOfInterview() {
		return typeOfInterview;
	}

	public void setTypeOfInterview(String typeOfInterview) {
		this.typeOfInterview = typeOfInterview;
	}

	public boolean isI_status() {
		return i_status;
	}

	public void setI_status(boolean i_status) {
		this.i_status = i_status;
	}

	public User getUser6() {
		return user6;
	}

	public void setUser6(User user6) {
		this.user6 = user6;
	}

}
