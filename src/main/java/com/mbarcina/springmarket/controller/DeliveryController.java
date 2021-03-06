package com.mbarcina.springmarket.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.classes.CartProduct;
import com.mbarcina.springmarket.entity.Delivery;
import com.mbarcina.springmarket.entity.DeliveryMethod;
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
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressService addressService; 
	
	@Autowired
	private ICreditCardService creditCardService; 
	
	@Autowired
	private IDeliveryService deliveryService;
	
	@Autowired
	private IDeliveryMethodService deliveryMethodService;
	
	@Autowired
	private IProductService productService; 
	
	@RequestMapping(value= {"/createDelivery"}, method=RequestMethod.POST)
	public @ResponseBody void createDelivery(HttpSession session, @RequestBody CartProduct[] productsShort) {
		Delivery newDelivery = new Delivery();
		
		BigDecimal totalCost = new BigDecimal(0);
		
		for(CartProduct product:productsShort) {
			ProductDelivery auxProduct = new ProductDelivery(productService.getProduct(product.getId()), newDelivery ,product.getPrice(), product.getQuantity());
			newDelivery.addProduct(auxProduct);
			BigDecimal auxQuantity = new BigDecimal(product.getQuantity());
			totalCost = totalCost.add(
				product.getPrice().multiply(auxQuantity)
			);
		}
		
		newDelivery.setProductTotalCost(totalCost);
		
		session.setAttribute("delivery", newDelivery);
	}
	
	@RequestMapping(value= {"/checkout"}, method=RequestMethod.GET)
	public ModelAndView checkout(HttpSession session) {		
		ModelAndView modelAndView = new ModelAndView();
		
		User loggedUser = Utils.getUtils().getLoggedUser(userService);
		Delivery myDelivery = (Delivery) session.getAttribute("delivery");
		
		modelAndView.addObject("delivery", myDelivery);
		modelAndView.addObject("user", loggedUser);
		
		List<DeliveryMethod> deliveryMethodList = deliveryMethodService.getDeliveryMethodList();
		modelAndView.addObject("deliveryMethodList", deliveryMethodList);

		modelAndView.addObject("canEditUserDetails", false);
		modelAndView.addObject("canEditCart", false);
		modelAndView.addObject("showAllProducts", false);
		
		modelAndView.setViewName("checkout");
		
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
		newDelivery.setBillingAccount(this.creditCardService.getCreditCardById(selectedCreditCardId));
		newDelivery.setDeliveryMethod(this.deliveryMethodService.getDeliveryMethodById(selectedDeliveryMethodId));
		BigDecimal totalCost = newDelivery.getProductTotalCost().add(newDelivery.getDeliveryMethod().getPrice());
		newDelivery.setDeliveryTotalCost(totalCost);
		newDelivery.setDeliveryOrderDate(new Date(System.currentTimeMillis()));
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, newDelivery.getDeliveryMethod().getEstimatedDays());
		newDelivery.setEstimatedDeliveryDate(c.getTime());
		
		// add new delivery to user
		User user = Utils.getUtils().getLoggedUser(userService);
		user.addDelivery(newDelivery);
		
		deliveryService.saveDelivery(newDelivery);
		
		modelAndView.setViewName("confirm_delivery");
		
		return modelAndView;
	}

}
