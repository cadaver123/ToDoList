package com.todo.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.todo.selenium.config.AbstractSelenium;
import com.todo.selenium.config.ToDoPage;

public class SeleniumToDoTest extends AbstractSelenium {
	private ToDoPage tdp;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		tdp = openToDoPage();
	}

	@Test
	public void shouldAddAndRemove() {
		String task = "pobudka";
		tdp.addTask(task);
		assertTrue(tdp.taskExists(task));
		tdp.removeTask(task);
		assertTrue(!tdp.taskExists(task));
	}
}
