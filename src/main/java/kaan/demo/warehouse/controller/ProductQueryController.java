package kaan.demo.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kaan.demo.warehouse.service.ProductService;

@Controller
public class ProductQueryController {
	@Autowired
	private ProductService productService;

	@GetMapping("/query-all")
	public ResponseEntity queryAllProducts() {
		return new ResponseEntity<>(productService.queryAll(), HttpStatus.OK);
	}
}
