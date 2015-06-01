package com.todo.controllers;
 


import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Task;
import com.todo.model.TaskDao;

@RestController
@RequestMapping("/json")
public class TodoRestController {
	
	@Autowired
	private TaskDao taskdao;
	
//	private static final Logger log = Logger.getLogger(TodoRestController.class);

	@RequestMapping(method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	private List<Task> returnTodoList(ModelMap model){
		return taskdao.getTasks();
	}
	


	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public List<Task> addTask(ModelMap model, @RequestParam(value = "task", required = true) String s) {
		
		if(s.length()>0) {
			taskdao.addTask(new Task(s));
		}
		
		return taskdao.getTasks();
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public List<Task> removeTask(ModelMap model, @RequestParam(value = "task", required = true) String s) {
		
	
		taskdao.removeTask(new Task(s));
		
		return taskdao.getTasks();
	}
 
}