package kaan.demo.warehouse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kaan.demo.warehouse.repo.InventoryRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleRepoTest {
	@Autowired
	private InventoryRepository repo;
	private static Article article;

	@BeforeAll
	public static void init() {
		article = new Article();
		article.setArt_id(100);
		article.setName("test");
		article.setStock(13);
	}

	@Test
	@DisplayName("Create Article Test")
	public void createArticleTest() {
		Article a = repo.save(article);
		assertEquals(a.getArt_id(), article.getArt_id());
	}
}
