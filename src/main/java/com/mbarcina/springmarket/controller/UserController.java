package com.mbarcina.springmarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.entity.Address;
import com.mbarcina.springmarket.entity.CreditCard;
import com.mbarcina.springmarket.entity.Product;
import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IAddressService;
import com.mbarcina.springmarket.repository.ICreditCardService;
import com.mbarcina.springmarket.repository.IProductService;
import com.mbarcina.springmarket.repository.IUserService;
import com.mbarcina.springmarket.utils.Utils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IProductService productService; 
	
	@Autowired
	private IAddressService addressService; 
	
	@Autowired
	private ICreditCardService creditCardService; 
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView getUserProfile() {
		ModelAndView modelAndView = new ModelAndView();
		
		// add the customer to the model
		User user = Utils.getUtils().getLoggedUser(userService);
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName("profile");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value= {"/cart"}, method=RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView modelAndView = new ModelAndView();
		
		// get products from the service
		List<Product> theProducts = productService.getProductList();
		modelAndView.addObject("products", theProducts);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/saveAddress"}, method=RequestMethod.GET)
	public ModelAndView getAddressForm() {
		ModelAndView modelAndView = new ModelAndView();
		
		Address address = new Address();
		address.setCountry("ES");
		modelAndView.addObject("address", address);
		modelAndView.setViewName("add_address");
		
		return modelAndView;
	}

	
	@RequestMapping(value= {"/saveAddress"}, method=RequestMethod.POST)
	public ModelAndView saveAddress(@Valid Address pAddress, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("address", pAddress);
			modelAndView.setViewName("add_address");
        } else {
        	User user = Utils.getUtils().getLoggedUser(userService);
    	    user.addAddress(pAddress);
    	    
    	    addressService.saveAddress(pAddress);
    	    
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/editAddress"}, method=RequestMethod.GET)
	public ModelAndView getAddressEditionForm(Model pModel) {
		ModelAndView modelAndView = new ModelAndView();
		
		Address address = new Address();
		address.setCountry("ES");
		modelAndView.addObject("address", address);
		modelAndView.setViewName("add_address");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value= {"/deleteAddress"}, method=RequestMethod.POST)
	public String deleteAddress(Model pModel) {
		// TODO
		return "";
	}
	
	@RequestMapping(value= {"/saveCreditCard"}, method=RequestMethod.GET)
	public ModelAndView getCreditCardForm() {		
		ModelAndView modelAndView = new ModelAndView();
		
		CreditCard creditCard = new CreditCard();
		modelAndView.addObject("creditCard", creditCard);
		modelAndView.setViewName("add_credit_card");
		
		return modelAndView;
	}

	
	@RequestMapping(value= {"/saveCreditCard"}, method=RequestMethod.POST)
	public ModelAndView saveCreditCard(@Valid CreditCard pCreditCard, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("address", pCreditCard);
			modelAndView.setViewName("add_credit_card");
        } else {
        	User user = Utils.getUtils().getLoggedUser(userService);
    	    user.addCreditCard(pCreditCard);
    	    
    	    creditCardService.saveCreditCard(pCreditCard);
    	    
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/editCreditCard"}, method=RequestMethod.GET)
	public ModelAndView getCreditCardEditionForm(Model pModel) {
		ModelAndView modelAndView = new ModelAndView();
		
		CreditCard creditCard = new CreditCard();
		modelAndView.addObject("creditCard", creditCard);
		modelAndView.setViewName("add_credit_card");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/deleteCreditCard"}, method=RequestMethod.POST)
	public String deleteCreditCard(Model pModel) {
		// TODO
		return "";
	}
}
