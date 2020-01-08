package com.mbarcina.springmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView getUserProfile() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("admin");
		
		return modelAndView;
	}

}
