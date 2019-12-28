package com.mbarcina.springmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/creditcard")
public class CreditCardController {
	
	@RequestMapping("/addCreditCardAccount")
	public String addCreditCardAccount() {
		return "add_credit_card_account";
	}
}
