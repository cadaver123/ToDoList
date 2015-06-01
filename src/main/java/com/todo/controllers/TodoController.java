package com.todo.controllers;
 


//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.model.Task;
import com.todo.model.TaskDao;

@Controller
@RequestMapping("/")
public class TodoController {
	
	@Autowired
	private TaskDao taskdao;
	
//	private static final Logger log = Logger.getLogger(TodoController.class);

	private String returnViewWithTodoList(ModelMap model){
		model.addAttribute("list", taskdao.getTasksAsStrings());
		return "todolist";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String displayList(ModelMap model) {
		return returnViewWithTodoList(model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTask(ModelMap model, @RequestParam(value = "task", required = true) String s) {
		if(s.length()>0) {
			taskdao.addTask(new Task(s));
		}
		
		return returnViewWithTodoList(model);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeTask(ModelMap model, @RequestParam(value = "task", required = true) String s) {
		
	
		taskdao.removeTask(new Task(s));
		
		return returnViewWithTodoList(model);
	}
 
}