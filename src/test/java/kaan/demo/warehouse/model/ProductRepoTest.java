package kaan.demo.warehouse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kaan.demo.warehouse.repo.InventoryRepository;
import kaan.demo.warehouse.repo.ProductArticleRepo;
import kaan.demo.warehouse.repo.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepoTest {
	@Autowired
	private ProductArticleRepo repo;
	@Autowired
	private ProductArticleRepo productArticleRepo;
	private static Product product;
	private static Article article1;
	private static Article article2;
	@Autowired
	private InventoryRepository inventoryRepo;
	private static ProductArticle productArticle1;
	private static ProductArticle productArticle2;
	@Autowired
	private ProductRepository productRepo;

	@BeforeAll
	public static void init() {
		article1 = new Article();
		article1.setArt_id(100);
		article1.setName("test");
		article1.setStock(13);
		article2 = new Article();
		article2.setArt_id(101);
		article2.setName("art2");
		article2.setStock(19);
		productArticle1 = new ProductArticle();
		productArticle1.setArt_id(100);
		productArticle1.setArticle(article1);
		productArticle1.setAmount_of(23);
		productArticle2 = new ProductArticle();
		productArticle2.setArt_id(101);
		productArticle2.setArticle(article2);
		productArticle2.setAmount_of(28);
		List<ProductArticle> productArticles = new ArrayList<>();
		productArticles.add(productArticle1);
		productArticles.add(productArticle2);
		product = new Product();
		product.setName("myProduct");
		product.setContain_articles(productArticles);
	}

	@Test
	@DisplayName("Create Productarticle Test")
	public void createArticleTest() {
		inventoryRepo.save(article1);
		inventoryRepo.save(article2);
		productArticleRepo.save(productArticle1);
		productArticleRepo.save(productArticle2);
		Product p = productRepo.save(product);
		assertEquals(p.getContain_articles().get(0).getArt_id(), article1.getArt_id());
	}

}
