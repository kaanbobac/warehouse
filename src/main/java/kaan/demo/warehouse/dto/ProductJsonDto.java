package kaan.demo.warehouse.dto;

import java.util.List;

import kaan.demo.warehouse.model.Product;
import lombok.Data;

@Data
public class ProductJsonDto {
	private List<Product> products;
}
