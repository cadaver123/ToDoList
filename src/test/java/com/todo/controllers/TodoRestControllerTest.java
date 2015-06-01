package com.todo.controllers;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.http.MediaType;
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

class TestUtil {
	 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                        MediaType.APPLICATION_JSON.getSubtype(),                        
                                                                        Charset.forName("utf8")                     
                                                                        );
}


@RunWith(MockitoJUnitRunner.class)
public class TodoRestControllerTest {
	
	//private static final Logger log = Logger.getLogger(TodoRestControllerTest.class);
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	TaskDao taskDaoMock;
	
	@Spy
	@InjectMocks
	TodoRestController tdc = new TodoRestController();
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
		List<Task> l1 = Arrays.asList(new Task("kara"), new Task("dziara"));
		when(taskDaoMock.getTasks()).thenReturn(l1);	
        mockMvc = MockMvcBuilders.standaloneSetup(tdc)
                .build();
    }
	  
	void performMvcMockTest(String url, boolean get) throws Exception {
		ResultActions ra = get ? mockMvc.perform(get(url)): mockMvc.perform(post(url));
		
		   ra.andExpect(status().isOk())
		   .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$", hasSize(2)))
		   .andExpect(jsonPath("$[0].task", is("kara")))
		   .andExpect(jsonPath("$[1].task", is("dziara")));
	}
	
	@Test
	public void testDisplayList() throws Exception  {
		performMvcMockTest("/json",true);
	}
	

	@Test
	public void shouldCallDaoForAddingWhenTaskIsNotEmpty() throws Exception {

        performMvcMockTest("/json/add?task=nara",false);
		
		ArgumentCaptor<Task> argument = ArgumentCaptor.forClass(Task.class);
		verify(taskDaoMock).addTask(argument.capture());
		assertEquals("nara", argument.getValue().getTask());

	}
	@Test
	public void shouldNotCallDaoForAddingWhenTaskIsEmpty() throws Exception {

        performMvcMockTest("/json/add?task=",false);
		
		verify(taskDaoMock, never()).removeTask(new Task(""));

	}

	@Test
	public void shouldCallDaoForRemovingWhenTaskIsNotEmpty() throws Exception {
         performMvcMockTest("/json/remove?task=nara",false);
		
		 ArgumentCaptor<Task> argument = ArgumentCaptor.forClass(Task.class);
		 verify(taskDaoMock).removeTask(argument.capture());
		 assertEquals("nara", argument.getValue().getTask());
		
		
	}
	
	@Test
	public void shouldNotCallDaoForRemovingTaskWhenTaskIsEmpty() throws Exception {
        performMvcMockTest("/json/remove?task=",false);

		 verify(taskDaoMock, never()).removeTask(new Task(""));
	}

}
