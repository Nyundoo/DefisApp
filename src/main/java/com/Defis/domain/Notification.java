package com.Defis.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Notification")
@Table(name = "notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer notificationId;

	private String message;

	private Date createdAt;

	private boolean isRead;

	@ManyToOne
	private User user;

	public Notification() {
	}

	public Notification(Integer notificationId, String message, Date createdAt, boolean isRead, User user) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.createdAt = createdAt;
		this.isRead = isRead;
		this.user = user;
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
