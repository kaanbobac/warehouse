package kaan.demo.warehouse.model;

import java.util.List;

import lombok.Data;

/**
 * Wrapper Class for handling Inventory Json Parsing
 * 
 * @author Kaan Bobac
 *
 */
@Data
public class InventoryWrapper {
	private List<Article> inventory;
}
