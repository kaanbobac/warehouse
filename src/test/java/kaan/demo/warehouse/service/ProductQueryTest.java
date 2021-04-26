package kaan.demo.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kaan.demo.warehouse.dto.ProductJsonDto;

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test02-inventory.json",
		"app.data.product.json.path=/test/json/test02-products.json" })
public class ProductQueryTest {
	@Autowired
	private ProductService productService;
	private static List<ProductJsonDto> dtoList;

	@BeforeAll
	public static void init() {
		dtoList = new ArrayList<>();
		ProductJsonDto first = new ProductJsonDto();
		first.setName("Dining Chair");
		first.setQuantity(0);
		ProductJsonDto second = new ProductJsonDto();
		second.setName("Dinning Table");
		second.setQuantity(0);
		dtoList.add(first);
		dtoList.add(second);
	}

	@Test
	public void queryAllProductsSize() {
		List<ProductJsonDto> products = productService.queryAll();
		assertEquals(products.size(), 2);
	}

	@Test
	public void queryAllProductsContent1() {
		List<ProductJsonDto> products = productService.queryAll();
		assertEquals(products.get(0).getQuantity(), 2);
	}

	@Test
	public void queryAllProductsContent2() {
		List<ProductJsonDto> products = productService.queryAll();
		assertEquals(products.get(1).getQuantity(), 3);
	}
}
