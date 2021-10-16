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

/**
 * @author defis
 *
 */
@Entity(name = "Payment")
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(length = 20, nullable = false, unique = false)
	private Integer amount_paid;

	@Column(name = "date_paid", length = 16, nullable = false)
	private Date date_paid;

	@ManyToOne
	@JoinColumn(name = "user_id8")
	private User user8;

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

	public Integer getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(Integer amount_paid) {
		this.amount_paid = amount_paid;
	}

	public Date getDate_paid() {
		return date_paid;
	}

	public void setDate_paid(Date date_paid) {
		this.date_paid = date_paid;
	}

	public User getUser8() {
		return user8;
	}

	public void setUser8(User user8) {
		this.user8 = user8;
	}

}
