package com.mbarcina.springmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView getErrorPage() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("error");
		
		return modelAndView;
	}
}
