package kaan.demo.warehouse.model;

import java.util.List;

import lombok.Data;

/**
 * Wrapper class for handling Product Json Parsing
 * 
 * @author Kaan Bobac
 *
 */
@Data
public class ProductWrapper {
	private List<Product> products;
}
