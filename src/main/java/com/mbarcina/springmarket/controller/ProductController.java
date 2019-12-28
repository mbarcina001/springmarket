package com.mbarcina.springmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mbarcina.springmarket.entity.Product;
import com.mbarcina.springmarket.repository.IProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/")
	public ModelAndView getProductList() {
		ModelAndView modelAndView = new ModelAndView();
		
		// get customers from the service
		List<Product> theProducts = productService.getProductList();
		modelAndView.addObject("products", theProducts);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}
	
	
	@RequestMapping("/search")
	public ModelAndView searchProduct(Model theModel) {
		ModelAndView modelAndView = new ModelAndView();
		
		// get customers from the service
		List<Product> theProducts = productService.getProductList();
		modelAndView.addObject("products", theProducts);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}

}
