package kaan.demo.warehouse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kaan.demo.warehouse.repo.InventoryRepository;
import kaan.demo.warehouse.repo.ProductArticleRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ProductArticleRepoTest {
	@Autowired
	private ProductArticleRepo repo;
	private static Article article;
	private static ProductArticle productArticle;
	@Autowired
	private InventoryRepository inventoryRepo;

	@BeforeAll
	public static void init() {
		article = new Article();
		article.setArt_id(100);
		article.setName("test");
		article.setStock(13);
		productArticle = new ProductArticle();
		productArticle.setArt_id(100);
		productArticle.setArticle(article);
		productArticle.setAmount_of(23);
	}

	@Test
	@DisplayName("Create Productarticle Test")
	public void createArticleTest() {
		inventoryRepo.save(article);
		ProductArticle p = repo.save(productArticle);
		assertEquals(p.getArticle().getArt_id(), article.getArt_id());
	}
}
