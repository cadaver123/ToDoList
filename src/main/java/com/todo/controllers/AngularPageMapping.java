package com.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/angular")
public class AngularPageMapping {
	
	@RequestMapping(method = RequestMethod.GET)
	public String displayList(ModelMap model) {
		return "angular";
	}
}
