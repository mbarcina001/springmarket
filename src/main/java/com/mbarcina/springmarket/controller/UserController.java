package com.mbarcina.springmarket.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.classes.CartProduct;
import com.mbarcina.springmarket.entity.Address;
import com.mbarcina.springmarket.entity.CreditCard;
import com.mbarcina.springmarket.entity.Delivery;
import com.mbarcina.springmarket.entity.DeliveryMethod;
import com.mbarcina.springmarket.entity.Product;
import com.mbarcina.springmarket.entity.ProductDelivery;
import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IAddressService;
import com.mbarcina.springmarket.repository.ICreditCardService;
import com.mbarcina.springmarket.repository.IDeliveryMethodService;
import com.mbarcina.springmarket.repository.IDeliveryService;
import com.mbarcina.springmarket.repository.IProductService;
import com.mbarcina.springmarket.repository.IUserService;
import com.mbarcina.springmarket.utils.Utils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDeliveryMethodService deliveryMethodService;
	
	@Autowired
	private IDeliveryService deliveryService;
	
	@Autowired
	private IProductService productService; 
	
	@Autowired
	private IAddressService addressService; 
	
	@Autowired
	private ICreditCardService creditCardService; 
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public ModelAndView getUserProfile() {
		ModelAndView modelAndView = new ModelAndView();
		
		//get logged in email
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
		
		// get customer from the service
		User user = userService.findUserByEmail(email);
		// add the customer to the model
		modelAndView.addObject("user", user);
		
		
		// get delivery method list from the service
		List<DeliveryMethod> deliveryMethodList = deliveryMethodService.getDeliveryMethodList();
		// add the delivery method list to the model
		modelAndView.addObject("deliveryMethodList", deliveryMethodList);
		modelAndView.setViewName("profile");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value= {"/cart"}, method=RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView modelAndView = new ModelAndView();
		
		// get customers from the service
		List<Product> theProducts = productService.getProductList();
		modelAndView.addObject("products", theProducts);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/confirmDelivery"}, method=RequestMethod.POST)
	public ModelAndView confirmDelivery(
			HttpSession session, 
			@RequestParam(value = "selectedAddressId", required = false) Integer selectedAddressId,
			@RequestParam(value = "selectedCreditCardId", required = false) Integer selectedCreditCardId,
			@RequestParam(value = "selectedDeliveryMethodId", required = false) Integer selectedDeliveryMethodId
	) {	
		ModelAndView modelAndView = new ModelAndView();
				
		Delivery newDelivery = (Delivery) session.getAttribute("delivery");
		
		newDelivery.setSendAddress(this.addressService.getAddressById(selectedAddressId));
		newDelivery.setBillingAccount(this.creditCardService.getCreditCardById(selectedAddressId));
		newDelivery.setDeliveryMethod(this.deliveryMethodService.getDeliveryMethodById(selectedDeliveryMethodId));
		double totalCost = ((newDelivery.getProductTotalCost() + newDelivery.getDeliveryMethod().getPrice())*100)/100;
		newDelivery.setDeliveryTotalCost(totalCost);
		newDelivery.setDeliveryOrderDate(new Date(System.currentTimeMillis()));
		
		deliveryService.saveDelivery(newDelivery);
		
		modelAndView.setViewName("confirm_delivery");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/checkout"}, method=RequestMethod.GET)
	public ModelAndView checkout(HttpSession session) {		
		ModelAndView modelAndView = new ModelAndView();
		
		User loggedUser = Utils.getUtils().getLoggedUser(userService);
		Delivery myDelivery = (Delivery) session.getAttribute("delivery");
		
		modelAndView.addObject("products", myDelivery.getProductList());
		modelAndView.addObject("user", loggedUser);
		
		List<DeliveryMethod> deliveryMethodList = deliveryMethodService.getDeliveryMethodList();
		modelAndView.addObject("deliveryMethodList", deliveryMethodList);
		
		modelAndView.setViewName("checkout");
		
		return modelAndView;
	}
	
	@RequestMapping(value= {"/createDelivery"}, method=RequestMethod.POST)
	public @ResponseBody void createDelivery(HttpSession session, @RequestBody CartProduct[] productsShort) {
		User user = Utils.getUtils().getLoggedUser(userService);
		Delivery newDelivery = new Delivery();
		
		double totalCost = 0;
		
		for(CartProduct product:productsShort) {
			ProductDelivery auxProduct = new ProductDelivery(productService.getProduct(product.getId()), newDelivery ,product.getPrice(), product.getQuantity());
			newDelivery.addProduct(auxProduct);
			totalCost += product.getPrice() * product.getQuantity();
		}
		
		newDelivery.setProductTotalCost((totalCost*100)/100);
	    
		// add new delivery to user
		user.addDelivery(newDelivery);
		
		session.setAttribute("delivery", newDelivery);
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
        	//get logged in email
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	    String email = auth.getName();
    		
    		// get customer from the service
    	    User user = userService.findUserByEmail(email);
    	    user.addAddress(pAddress);
    	    userService.saveUser(user);
    	    
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
	public String saveCreditCard(Model pModel) {
		// TODO
		return "profile";
	}
	
	@RequestMapping(value= {"/editCreditCard"}, method=RequestMethod.GET)
	public String getCreditCardEditionForm(Model pModel) {
		// TODO
		return "";
	}
	
	@RequestMapping(value= {"/deleteCreditCard"}, method=RequestMethod.POST)
	public String deleteCreditCard(Model pModel) {
		// TODO
		return "";
	}
}
