package com.mbarcina.springmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbarcina.springmarket.entity.DeliveryMethod;
import com.mbarcina.springmarket.entity.Product;
import com.mbarcina.springmarket.repository.IDeliveryMethodService;
//import com.mbarcina.springmarket.repository.IUserService;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	/*@Autowired
	private IUserService userService;*/
	
	@Autowired
	private IDeliveryMethodService deliveryMethodService;
	
	@RequestMapping("/deliveryOrder")
	public String getDeliveryOrder(Model pModel, @ModelAttribute("user") Product[] pOrderList) {
		
		pModel.addAttribute("order_list", pOrderList);
		
		/*List<Address> userAddressList = userService.getCustomerAddressList();
		pModel.addAttribute("user_address_list", userAddressList);
		
		List<CreditCard> userCreditCardList = userService.getCustomerCreditCardList();
		pModel.addAttribute("user_billing_accounts", userCreditCardList);*/
		
		// Retrieve Delivery Method Options
		List<DeliveryMethod> deliveryMethodList = deliveryMethodService.getDeliveryMethodList();
		pModel.addAttribute("delivery_method_list", deliveryMethodList);
		
		return "delivery_order";
	}

}
