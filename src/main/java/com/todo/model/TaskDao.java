
package com.todo.model;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




@Repository
public class TaskDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void addTask(Task t) {
			sessionFactory.getCurrentSession().persist(t);
	}
	
	@Transactional
	public void removeTask(Task t) {
			sessionFactory.getCurrentSession().createQuery("delete from Task where task=?").setString(0,t.getTask()).executeUpdate();;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Task> getTasks() {
		
		//List<Task> tasks = new ArrayList<Task>();
		
		//tasks = sessionFactory.getCurrentSession().createQuery("from Task").list();
	
		return sessionFactory.getCurrentSession().createQuery("from Task").list();
	}
	@Transactional
	public List<String> getTasksAsStrings() {
		ArrayList<String> ts = new ArrayList<String>();
		for(Task t : getTasks())
			ts.add(t.getTask());
		return ts;
	}
		
}