package com.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.dao.ProductDao;
import com.product.entity.Product;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@GetMapping(path = "/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String productName ){
		try {
			List<Product> products = new ArrayList<Product>();
//			if(productName == null) {
				productDao.findAll().forEach(products::add);
			
//			else {
//				productDao.findByNameContaining(productName).forEach(products::add);
//				
//			}
			if(products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(path = "/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product _product = productDao.save(new Product(product.getProductName(), product.getProductPrice()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		}
		catch(Exception ex){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(path = "/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId){
		Optional<Product> productData = productDao.findById(productId);
		if(productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
	}

	@PutMapping(path = "/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product product){

			Optional<Product> productData= productDao.findById(productId);
			if(productData.isPresent()) {
				Product _product = productData.get();
				_product.setProductName(product.getProductName());
				return new ResponseEntity<>(productDao.save(_product), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
	
	@DeleteMapping(path = "/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") long productId) {
		try {
			productDao.deleteById(productId);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		catch(Exception ex) {
			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/products")
	public ResponseEntity<Product> deleteAllProducts(){
		try {
			productDao.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
