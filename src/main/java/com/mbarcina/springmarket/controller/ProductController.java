package com.mbarcina.springmarket.controller;

import java.util.List;

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
	public ModelAndView getProductList(
		@RequestParam(value = "nextPage", required = false) Integer nextPage,
		@RequestParam(value = "itemsPerPage", required = false) Integer itemsPerPage,
		@RequestParam(value = "searchTerm", required = false) String searchTerm
	) {
		System.out.println("Next Page: " + nextPage);
		System.out.println("Search Term: " + searchTerm);
		
		ModelAndView modelAndView = new ModelAndView();
		List<Product> theProducts;
		
		if(searchTerm != null) {
			theProducts = productService.searchProduct(searchTerm);
			modelAndView.addObject("showingSearchResult", true);
			modelAndView.addObject("searchTerm", searchTerm);
		}else {
			theProducts = productService.getProductList();
		}
		
		// get products from the service
		//theProducts.addAll(theProducts);
		modelAndView.addObject("products", theProducts);
		modelAndView.addObject("showingSearchResult", false);
		modelAndView.addObject("totalProducts", theProducts.size());
		if(nextPage==null) {
			modelAndView.addObject("actualPage", 1);
		}else {
			modelAndView.addObject("actualPage", nextPage);
		}
		
		if(itemsPerPage==null) {
			modelAndView.addObject("itemsPerPage", 12);
		}else {
			modelAndView.addObject("itemsPerPage", itemsPerPage);
		}
		
		modelAndView.setViewName("products");
		
		return modelAndView;
		
	}
}
