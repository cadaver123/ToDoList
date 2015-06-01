package com.todo.controllers;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.todo.model.Task;
import com.todo.model.TaskDao;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	TaskDao taskDaoMock;
	
	@Spy
	@InjectMocks
	TodoController tdc = new TodoController();
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
		when(taskDaoMock.getTasksAsStrings()).thenReturn(Arrays.asList("kara", "dziara"));	
        mockMvc = MockMvcBuilders.standaloneSetup(tdc)
                .build();
    }
	  

	void performMvcMockTest(String url, boolean get) throws Exception {
		ResultActions ra = get ? mockMvc.perform(get(url)): mockMvc.perform(post(url));

		   ra.andExpect(status().isOk())
		   .andExpect(view().name("todolist"))
		   .andExpect(forwardedUrl("todolist"))
//		   .andExpect(forwardedUrl("WEB-INF/pages/todolist.jsp"))
		   .andExpect(model().attribute("list", hasSize(2)))
		   .andExpect(model().attribute("list", hasItem("kara")))
		   .andExpect(model().attribute("list", hasItem("dziara")));
	}
	
	@Test
	public void testDisplayList() throws Exception  {
		performMvcMockTest("/",true);
	}
	

	@Test
	public void shouldCallDaoForAddingWhenTaskIsNotEmpty() throws Exception {

        performMvcMockTest("/add?task=nara",false);
		
		ArgumentCaptor<Task> argument = ArgumentCaptor.forClass(Task.class);
		verify(taskDaoMock).addTask(argument.capture());
		assertEquals("nara", argument.getValue().getTask());

	}
	@Test
	public void shouldNotCallDaoForAddingWhenTaskIsEmpty() throws Exception {

        performMvcMockTest("/add?task=",false);
		
		verify(taskDaoMock, never()).removeTask(new Task(""));

	}

	@Test
	public void shouldCallDaoForRemovingWhenTaskIsNotEmpty() throws Exception {
         performMvcMockTest("/remove?task=nara",false);
		
		 ArgumentCaptor<Task> argument = ArgumentCaptor.forClass(Task.class);
		 verify(taskDaoMock).removeTask(argument.capture());
		 assertEquals("nara", argument.getValue().getTask());
		
		
	}
	
	@Test
	public void shouldNotCallDaoForRemovingTaskWhenTaskIsEmpty() throws Exception {
        performMvcMockTest("/remove?task=",false);

		 verify(taskDaoMock, never()).removeTask(new Task(""));
	}

}

