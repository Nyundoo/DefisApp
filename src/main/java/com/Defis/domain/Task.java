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

	@Column(name = "create_task", length = 26, nullable = false)
	private String createTask;

	@Column(name = "task_description", length = 500, nullable = true)
	private String taskDescription;

	@Column(name = "created_by", length = 45, nullable = false, updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Column(name = "deadline_date", length = 16, nullable = false)
	private Date deadlineDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "responsible_applicants", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "applicant_id"))
	private Set<User> responsible = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "participants_applicants", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "applicant_id"))
	private Set<User> participants = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "observers_applicants", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "applicant_id"))
	private Set<User> observers = new HashSet<>();

	private boolean priority = false;

	private boolean task_completed = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateTask() {
		return createTask;
	}

	public void setCreateTask(String createTask) {
		this.createTask = createTask;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Set<User> getResponsible() {
		return responsible;
	}

	public void setResponsible(Set<User> responsible) {
		this.responsible = responsible;
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

	public boolean isTask_completed() {
		return task_completed;
	}

	public void setTask_completed(boolean task_completed) {
		this.task_completed = task_completed;
	}

}
