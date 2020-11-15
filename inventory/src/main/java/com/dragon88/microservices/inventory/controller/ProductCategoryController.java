/**
 * 
 */
package com.dragon88.microservices.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dragon88.microservices.inventory.dto.FullCategoryDTO;
import com.dragon88.microservices.inventory.dto.PrimaryCategoryDTO;
import com.dragon88.microservices.inventory.service.ProductService;

/**
 * @author dragon
 *
 */
@RestController
public class ProductCategoryController {

	@Autowired
	ProductService productService; 
	
	@RequestMapping(value="/category/primary", method= RequestMethod.GET, produces = "application/json")
	public List<PrimaryCategoryDTO> getPrimaryCategory(){        
		return productService.fetchPrimaryCategories();
	}
	
	@RequestMapping(value="/category/secondary", method= RequestMethod.GET, produces = "application/json")
	public List<FullCategoryDTO> getSecondaryCategories(@RequestParam("categoryId") String categoryId  ){        
		return productService.fetchAllCategoryById(categoryId);
	}
	
	

}
