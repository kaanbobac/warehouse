package kaan.demo.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kaan.demo.warehouse.model.Article;
import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.repo.InventoryRepository;
import kaan.demo.warehouse.repo.ProductArticleRepo;
import kaan.demo.warehouse.repo.ProductRepository;

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test01-inventory.json",
		"app.data.product.json.path=/test/json/test01-products.json" })
public class DataLoaderTest {
	@Autowired
	private ProductArticleRepo productArticleRepo;
	@Autowired
	private InventoryRepository inventoryRepo;

	@Autowired
	private ProductRepository productRepo;

	@Test
	public void checkSuccessLoadArticle() {
		List<Article> articles = (List<Article>) inventoryRepo.findAll();
		assertEquals(articles.size(), 10);
	}

	@Test
	public void checkSuccessLoadArticleEntityArtId() {
		List<Article> articles = (List<Article>) inventoryRepo.findAll();
		assertEquals(articles.get(4).getArt_id(), 5);
	}

	@Test
	public void checkSuccessLoadArticleEntityStock() {
		List<Article> articles = (List<Article>) inventoryRepo.findAll();
		assertEquals(articles.get(4).getStock(), 18);
	}

	@Test
	public void checkSuccessLoadArticleEntityName() {
		List<Article> articles = (List<Article>) inventoryRepo.findAll();
		assertEquals(articles.get(4).getName(), "five");
	}

	@Test
	public void checkSuccessLoadProduct() {
		List<Product> products = (List<Product>) productRepo.findAll();
		assertEquals(products.size(), 2);
	}

	@Test
	public void checkSuccessLoadProductName() {
		List<Product> products = (List<Product>) productRepo.findAll();
		assertEquals(products.get(1).getName(), "Dinning Table");
	}

	@Test
	public void checkSuccessLoadProductArticle() {
		List<Product> products = (List<Product>) productRepo.findAll();
		assertEquals(products.get(0).getContain_articles().get(0).getAmount_of(), 4);
	}
}
