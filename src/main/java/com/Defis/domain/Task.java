package com.Defis.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity(name = "Task")
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "task", length = 16, nullable = true)
	private String task;

	@Column(name = "task_description", length = 450, nullable = true)
	private String taskDescription;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Column(name = "deadline_date", length = 16, nullable = true)
	private Date deadlineDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "participants_applicants", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "applicant_id"))
	private Set<User> participants = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "observers_applicants", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "applicant_id"))
	private Set<User> observers = new HashSet<>();

	private boolean priority = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public Set<User> getObservers() {
		return observers;
	}

	public void setObservers(Set<User> observers) {
		this.observers = observers;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}



}
