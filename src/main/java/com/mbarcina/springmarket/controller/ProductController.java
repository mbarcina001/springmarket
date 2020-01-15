package com.mbarcina.springmarket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		// get products from the service
		List<Product> theProducts = productService.getProductList();
		modelAndView.addObject("products", theProducts);
		modelAndView.addObject("showingSearchResult", false);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}
	
	
	@RequestMapping("/search")
	public ModelAndView searchProduct(
			HttpSession session, 
			@RequestParam(value = "searchTerm", required = false) String searchTerm
	) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Product> theProducts = productService.searchProduct(searchTerm);
		modelAndView.addObject("products", theProducts);
		modelAndView.addObject("showingSearchResult", true);
		
		modelAndView.setViewName("products");
		
		return modelAndView;
	}

}
