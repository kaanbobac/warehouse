package kaan.demo.warehouse.model;

import java.util.List;

import lombok.Data;

@Data
public class InventoryWrapper {
	private List<Article> inventory;
}
