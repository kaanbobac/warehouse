package kaan.demo.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kaan.demo.warehouse.dto.ProductJsonDto;

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test03-inventory.json",
		"app.data.product.json.path=/test/json/test03-products.json" })
public class ProductSellTest {
	@Autowired
	private ProductService productService;

	@Test
	public void sellProduct() {
		List<ProductJsonDto> productAll = productService.queryAll();
		List<ProductJsonDto> products = productService.sellProduct(productAll.get(0).getId());
		assertEquals(products.size(), 3);
	}
}
