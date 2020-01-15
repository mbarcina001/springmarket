package com.mbarcina.springmarket.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.entity.Address;
import com.mbarcina.springmarket.entity.CreditCard;
import com.mbarcina.springmarket.entity.Delivery;
import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IAddressService;
import com.mbarcina.springmarket.repository.ICreditCardService;
import com.mbarcina.springmarket.repository.IUserService;
import com.mbarcina.springmarket.utils.Utils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressService addressService; 
	
	@Autowired
	private ICreditCardService creditCardService; 
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView getUserProfile() {
		return getProfileModelAndView(null);		
	}
	
	@RequestMapping(value= {"/cart"}, method=RequestMethod.GET)
	public ModelAndView showCart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		Delivery newDelivery = (Delivery) session.getAttribute("delivery");
		modelAndView.addObject("products", newDelivery.getProductList());
		modelAndView.addObject("totalPrice", newDelivery.getProductTotalCost());
		modelAndView.addObject("canEditCart", true);
		modelAndView.setViewName("cart");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/updateProfile"}, method=RequestMethod.POST)
	public ModelAndView updateProfile(@Valid User pUser, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user = Utils.getUtils().getLoggedUser(userService);
		
		if (bindingResult.hasErrors()) {
			for(int i=0; i<bindingResult.getFieldErrorCount(); i++) {
				System.out.println(bindingResult.getFieldErrors().get(i));
			}
			pUser.setAddressList(user.getAddressList());
			pUser.setCardList(user.getCardList());
			modelAndView = getProfileModelAndView(pUser);
		}else {
			user.setName(pUser.getName());
			user.setEmail(pUser.getEmail());
			userService.updateUser(user);
			Utils.getUtils().setLoggedUser(user);
			
			modelAndView = getProfileModelAndView(user);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/changePassword"}, method=RequestMethod.GET)
	public ModelAndView getChangePassword() {
		ModelAndView modelAndView = new ModelAndView();
		
		User user = Utils.getUtils().getLoggedUser(userService);
		
		modelAndView.addObject("user", user);
		modelAndView.setViewName("change_password");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/changePassword"}, method=RequestMethod.POST)
	public ModelAndView postChangePassword(@Valid User pUser, BindingResult bindingResult) {
		System.out.println("oldPassword: " + pUser.getOldPassword());
		System.out.println("newPassword: " + pUser.getPassword());
		System.out.println("repeatNewPassword: " + pUser.getRetypePassword());

		ModelAndView modelAndView = new ModelAndView();
		User user = Utils.getUtils().getLoggedUser(userService);
		boolean error = false;
		
		if(pUser.getOldPassword()==null || pUser.getOldPassword().equals("")) {
			bindingResult.rejectValue("password", "error.user", "Old password can't be empty");
			error = true;
		} else if(pUser.getPassword()==null || pUser.getPassword().equals("")) {
			bindingResult.rejectValue("password", "error.user", "New password can't be empty");
			error = true;
		} else if(pUser.getRetypePassword()==null || pUser.getRetypePassword().equals("")) {
			bindingResult.rejectValue("password", "error.user", "Retype new password can't be empty");
			error = true;
		} else if(!userService.isPasswordEquals(user, pUser.getOldPassword())){
			bindingResult.rejectValue("password", "error.user", "Old password is not correct");
			error = true;
		}else if(!pUser.getPassword().equals(pUser.getRetypePassword())) {
			bindingResult.rejectValue("password", "error.user", "Passwords are not the same");
			error = true;
		}
		
		if(error) {
			modelAndView.addObject("user", pUser);
			System.out.println("USER");
			System.out.println(pUser);
			modelAndView.setViewName("change_password");
		}else {
			user.setPassword(userService.getPasswordEncoded(pUser.getPassword()));
			userService.updateUser(user);
			modelAndView = getProfileModelAndView(user);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/saveAddress"}, method=RequestMethod.GET)
	public ModelAndView getAddressForm() {
		ModelAndView modelAndView = new ModelAndView();
		
		Address address = new Address();
		address.setCountry("ES");
		modelAndView.addObject("address", address);
		modelAndView.addObject("isEditing", false);
		modelAndView.setViewName("add_address");
		
		return modelAndView;
	}

	
	@RequestMapping(value= {"/saveAddress"}, method=RequestMethod.POST)
	public ModelAndView saveAddress(@Valid Address pAddress, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {	
			for(int i=0; i<bindingResult.getFieldErrorCount(); i++) {
				System.out.println(bindingResult.getFieldErrors().get(i));
			}		
			modelAndView.addObject("address", pAddress);
			modelAndView.addObject("isEditing", false);
			modelAndView.setViewName("add_address");
        } else {
        	User user = Utils.getUtils().getLoggedUser(userService);
    	    user.addAddress(pAddress);
    	    userService.updateUser(user);
    	    
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		System.out.println("pAddress");
		System.out.println(pAddress);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value= {"/editAddress"}, method=RequestMethod.GET)
	public ModelAndView getAddressEditionForm(
		@RequestParam(value = "selectedAddressId", required = false) Integer selectedAddressId
	) {
		ModelAndView modelAndView = new ModelAndView();
		
		Address address = addressService.getAddressById(selectedAddressId);
		User user = Utils.getUtils().getLoggedUser(userService);
		
		if(!user.getAddressList().contains(address)) {
			modelAndView = new ModelAndView("redirect:/error/");
		}else {
			modelAndView.addObject("address", address);
			modelAndView.addObject("isEditing", true);
			modelAndView.setViewName("add_address");
		}
				
		return modelAndView;
	}
	
	@RequestMapping(value= {"/editAddress"}, method=RequestMethod.POST)
	public ModelAndView editAddress(@Valid Address pAddress, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("address", pAddress);
			modelAndView.addObject("isEditing", true);
			modelAndView.setViewName("add_address");
        } else {
        	addressService.updateAddress(pAddress);
    	    
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/deleteAddress"}, method=RequestMethod.GET)
	public ModelAndView deleteAddress(
		@RequestParam(value = "selectedAddressId", required = false) Integer selectedAddressId
	) {
		ModelAndView modelAndView = new ModelAndView();
		
		Address address = addressService.getAddressById(selectedAddressId);
		User user = Utils.getUtils().getLoggedUser(userService);
		int index = user.getAddressList().indexOf(address);
		
		if(index == -1) {
			modelAndView = new ModelAndView("redirect:/error/");
		}else {
			user.getAddressList().remove(index);
			userService.updateUser(user);

    	    try {
    	    	addressService.deleteAddress(selectedAddressId);
    	    }catch(Exception e) {
    	    	// Might get an exception if a delivery has been sent to this address
    	    	e.printStackTrace();
    	    }
    	    
			modelAndView = new ModelAndView("redirect:/user/");
		}
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(value= {"/saveCreditCard"}, method=RequestMethod.GET)
	public ModelAndView getCreditCardForm() {		
		ModelAndView modelAndView = new ModelAndView();
		
		CreditCard creditCard = new CreditCard();
		modelAndView.addObject("creditCard", creditCard);
		modelAndView.addObject("isEditing", false);
		modelAndView.setViewName("add_credit_card");
		
		return modelAndView;
	}
	

	@RequestMapping(value= {"/saveCreditCard"}, method=RequestMethod.POST)
	public ModelAndView saveCreditCard(@Valid CreditCard pCreditCard, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			for(int i=0; i<bindingResult.getFieldErrorCount(); i++) {
				System.out.println(bindingResult.getFieldErrors().get(i));
			}
			modelAndView.addObject("creditCard", pCreditCard);
			modelAndView.addObject("isEditing", false);
			modelAndView.setViewName("add_credit_card");
        } else {
        	User user = Utils.getUtils().getLoggedUser(userService);
    	    user.addCreditCard(pCreditCard);
    	    userService.updateUser(user);
    	    
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		return modelAndView;
	}
	
	
	@RequestMapping(value= {"/editCreditCard"}, method=RequestMethod.GET)
	public ModelAndView getCreditCardEditionForm(
		@RequestParam(value = "selectedCreditCardId", required = false) Integer selectedCreditCardId
	) {
		ModelAndView modelAndView = new ModelAndView();
		
		CreditCard creditCard = creditCardService.getCreditCardById(selectedCreditCardId);
		User user = Utils.getUtils().getLoggedUser(userService);
		
		if(!user.getCardList().contains(creditCard)) {
			modelAndView = new ModelAndView("redirect:/error/");
		}else {
			modelAndView.addObject("creditCard", this.ofuscateCreditCard(creditCard));
			modelAndView.addObject("isEditing", true);
			modelAndView.setViewName("add_credit_card");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/editCreditCard"}, method=RequestMethod.POST)
	public ModelAndView editCreditCard(@Valid CreditCard pCreditCard, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("creditCard", pCreditCard);
			modelAndView.addObject("isEditing", true);
			modelAndView.setViewName("add_credit_card");
        } else {
        	CreditCard updatedCreditCard = creditCardService.getCreditCardById(pCreditCard.getId());
        	updatedCreditCard.setExpirationDateMonth(pCreditCard.getExpirationDateMonth());
        	updatedCreditCard.setExpirationDateYear(pCreditCard.getExpirationDateYear());
        	updatedCreditCard.setHolder(pCreditCard.getHolder());
        	
        	creditCardService.updateCreditCard(updatedCreditCard);
        	
        	modelAndView = new ModelAndView("redirect:/user/");
        }
		
		return modelAndView;
	}
	
	
	@RequestMapping(value= {"/deleteCreditCard"}, method=RequestMethod.GET)
	public ModelAndView deleteCreditCard(
		@RequestParam(value = "selectedCreditCardId", required = false) Integer selectedCreditCardId		
	) {		
		ModelAndView modelAndView = new ModelAndView();
		
		CreditCard creditCard = creditCardService.getCreditCardById(selectedCreditCardId);
		User user = Utils.getUtils().getLoggedUser(userService);
		int index = user.getCardList().indexOf(creditCard);
		
		if(index == -1) {
			modelAndView = new ModelAndView("redirect:/error/");
		}else {
			user.getCardList().remove(index);
			user.deleteCreditCard(creditCard);
    	    userService.updateUser(user);
    	    try {
    	    	creditCardService.deleteCreditCard(selectedCreditCardId);	
    	    }catch(Exception e) {
    	    	// Might get an exception if a delivery has been paid using this credit card
    	    	e.printStackTrace();
    	    }
    	    
			modelAndView = new ModelAndView("redirect:/user/");
		}
		
		return modelAndView;
	}
	
	private CreditCard ofuscateCreditCard(CreditCard pCreditCard) {
		pCreditCard.setNumber(
			"Ends with " + pCreditCard.getNumber().substring(
				pCreditCard.getNumber().length()-4, 
				pCreditCard.getNumber().length()
			)
		);
		pCreditCard.setCvc("000");
		
		System.out.println(pCreditCard);
		
		return pCreditCard;
	}
	
	private ModelAndView getProfileModelAndView(User pUser) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(pUser==null) {
			pUser = Utils.getUtils().getLoggedUser(userService);	
		}
		
		// ofuscate credit card numbers and cvc
		for(int i=0; i<pUser.getCardList().size(); i++) {
			pUser.getCardList().set(i, this.ofuscateCreditCard(pUser.getCardList().get(i)));
		}
		
		pUser.setPassword("****");

		modelAndView.addObject("name", pUser.getName());
		modelAndView.addObject("isAdmin", userService.isUserAdmin(pUser));
		modelAndView.addObject("user", pUser);
		modelAndView.addObject("canEditUserDetails", true);
		modelAndView.setViewName("profile");
		
		System.out.println("User");
		System.out.println(pUser);
		
		return modelAndView;
	}
}
