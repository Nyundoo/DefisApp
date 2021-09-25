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

@Entity(name = "Training")
@Table(name = "training")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "start_date", length = 16, nullable = false)
	private Date start_date;

	@Column(name = "finish_date", length = 16, nullable = false)
	private Date finish_date;

	@Column(name = "cert_no", length = 240, nullable = true)
	private BigInteger cert_no;

	private boolean t_status = false;

	@ManyToOne
	@JoinColumn(name = "user_id5")
	private User user5;

	public Training() {
	}

	public Training(Integer id, Applicant applicant, Date start_date, Date finish_date, BigInteger cert_no) {
		super();
		this.id = id;
		this.applicant = applicant;
		this.start_date = start_date;
		this.finish_date = finish_date;
		this.cert_no = cert_no;
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}

	public BigInteger getCert_no() {
		return cert_no;
	}

	public void setCert_no(BigInteger cert_no) {
		this.cert_no = cert_no;
	}

	public boolean isT_status() {
		return t_status;
	}

	public void setT_status(boolean t_status) {
		this.t_status = t_status;
	}

	public User getUser5() {
		return user5;
	}

	public void setUser5(User user5) {
		this.user5 = user5;
	}

}
