package com.todo.model;

import static org.junit.Assert.*;


import java.util.List;

import javax.transaction.Transactional;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.todo.config.AppConfig;
import com.todo.config.HibernateConfig;
import com.todo.model.Task;
import com.todo.model.TaskDao;


@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@TransactionConfiguration(defaultRollback=true) 
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskDaoTest {
	
	//private static final Logger log = Logger.getLogger(TodoRestControllerTest.class);
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired
	private TaskDao tdao;

	

	@Test
	@Transactional
	@Sql(scripts="classpath:testDBconfig.sql")
	public void getTasks() throws Exception {
		List<Task> tl =  tdao.getTasks();
		
		assertEquals(2,tl.size());
		
		assertEquals(tl.get(0).getTask(), "kara");
		assertEquals(tl.get(1).getTask(), "dziara");

	}
	
	@Test
	@Transactional
	@Sql(scripts="classpath:testDBconfig.sql")
	public void getTasksAsStrings() throws Exception {
		List<String> tl =  tdao.getTasksAsStrings();
		
		assertEquals(2,tl.size());
		
		assertEquals(tl.get(0), "kara");
		assertEquals(tl.get(1), "dziara");

	}
	
	@Test
	@Transactional
	@Sql(scripts="classpath:testDBconfig.sql")
	public void addTask() throws Exception {
		tdao.addTask(new Task("nara"));
		
		List<String> tl =  tdao.getTasksAsStrings();
		
		assertEquals(3,tl.size());
		
		assertEquals(tl.get(0), "kara");
		assertEquals(tl.get(1), "dziara");
		assertEquals(tl.get(2), "nara");

	}
	
	@Test
	@Transactional
	@Sql(scripts="classpath:testDBconfig.sql")
	public void removeTask() throws Exception {
		tdao.removeTask(new Task("dziara"));;
		
		List<String> tl =  tdao.getTasksAsStrings();
		
		assertEquals(1,tl.size());
		
		assertEquals(tl.get(0), "kara");
		

	}

}

