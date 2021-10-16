package com.Defis.domain;

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

@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "ticket_date", length = 16, nullable = false)
	private Date ticket_date;

	@Column(name = "destination", length = 64, nullable = false)
	private String destination;

	@Column(name = "date_travel", length = 16, nullable = true)
	private Date date_travel;

	@Column(name = "flight_name", length = 64, nullable = false)
	private String flight_name;

	@Column(name = "expected_arrival_date", length = 16, nullable = true)
	private Date expected_arrival_date;

	private boolean travel_status = false;
	
	@Column(length = 64)
	private String photos;
	
	@ManyToOne
	@JoinColumn(name = "user_id4")
	private User user4;

	public Ticket() {
	}

	public Ticket(Integer id, Date ticket_date, String destination, Date date_travel, String flight_name,
			Date expected_arrival_date, boolean travel_status) {
		super();
		this.id = id;
		this.ticket_date = ticket_date;
		this.destination = destination;
		this.date_travel = date_travel;
		this.flight_name = flight_name;
		this.expected_arrival_date = expected_arrival_date;
		this.travel_status = travel_status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTicket_date() {
		return ticket_date;
	}

	public void setTicket_date(Date ticket_date) {
		this.ticket_date = ticket_date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate_travel() {
		return date_travel;
	}

	public void setDate_travel(Date date_travel) {
		this.date_travel = date_travel;
	}

	public String getFlight_name() {
		return flight_name;
	}

	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}

	public Date getExpected_arrival_date() {
		return expected_arrival_date;
	}

	public void setExpected_arrival_date(Date expected_arrival_date) {
		this.expected_arrival_date = expected_arrival_date;
	}

	public boolean isTravel_status() {
		return travel_status;
	}

	public void setTravel_status(boolean travel_status) {
		this.travel_status = travel_status;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public User getUser4() {
		return user4;
	}

	public void setUser4(User user4) {
		this.user4 = user4;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

}
