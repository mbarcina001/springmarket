package com.mbarcina.springmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {
	
	@RequestMapping("/addAddress")
	public String addAddress() {
		return "add_address";
	}

}
