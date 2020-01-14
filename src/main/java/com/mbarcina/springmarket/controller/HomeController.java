package com.mbarcina.springmarket.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IUserService;

@Controller
public class HomeController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("showRegister", false);
		modelAndView.addObject("user", new User());
        modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value= {"/register"}, method=RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new User());
		modelAndView.addObject("showRegister", true);
        modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(@Valid User user, BindingResult bindingResult) {
		System.out.println("Received user: " + user.toString());
		
		ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getFieldErrors());
    		modelAndView.addObject("showRegister", true);
            modelAndView.setViewName("home");
        } else {
            userService.createUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration_ok");
        }
        return modelAndView;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public @ResponseBody void logout(HttpSession session) {
		System.out.println("Logout");
	}
}
