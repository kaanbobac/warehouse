package kaan.demo.warehouse.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import kaan.demo.warehouse.dto.ProductJsonDto;
import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.repo.ProductRepository;

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test11-inventory.json",
		"app.data.product.json.path=/test/json/test11-products.json" })
@AutoConfigureMockMvc
public class SellOneAffectAllTest extends BaseTest {
	@Autowired
	private MockMvc mvc;
	private static List<ProductJsonDto> products = new ArrayList<>();
	private static String jsonResponse;
	private static String query = "/sell-product?id=";
	@Autowired
	private ProductRepository productRepo;

	public void init() {
		List<Product> database = (List<Product>) productRepo.findAll();
		query += database.get(2).getId();

		for (Product p : database) {
			ProductJsonDto product = new ProductJsonDto();
			String name = p.getName();
			product.setName(p.getName());
			product.setId(p.getId());
			if (name.equals("Dining Chair")) {
				product.setQuantity(0);
			} else if (name.equals("Dining Table")) {
				product.setQuantity(0);
			} else if (name.equals("New Product")) {
				product.setQuantity(0);
			}
			products.add(product);
		}
		jsonResponse = toJsonProduct(products);
	}

	@Test
	public void sellOne() throws Exception {
		init();
		mvc.perform(post(query).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonResponse));

	}
}