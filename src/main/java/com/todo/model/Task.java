package com.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tasksList")
public class Task {
	private String task;

	@Id
	@Column(name = "task", unique = false, nullable = false, length = 1000)
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public Task() { }
	public Task(String task) {
		this.task = task;
	}
	
}