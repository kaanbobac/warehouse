package kaan.demo.warehouse.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test04-inventory.json",
		"app.data.product.json.path=/test/json/test04-products.json" })
@AutoConfigureMockMvc
public class QueryAllProductsAvailableTest extends BaseTest {
	@Autowired
	private MockMvc mvc;
	private static List<ProductJsonDto> products = new ArrayList<>();
	private static String jsonResponse;
	@Autowired
	private ProductRepository productRepo;

	public void init() {
		List<Product> database = (List<Product>) productRepo.findAll();
		for (Product p : database) {
			ProductJsonDto product = new ProductJsonDto();
			String name = p.getName();
			product.setName(p.getName());
			product.setId(p.getId());
			if (name.equals("Dining Chair")) {
				product.setQuantity(2);
			} else if (name.equals("Dining Table")) {
				product.setQuantity(3);
			} else if (name.equals("New Product")) {
				product.setQuantity(1);
			}
			products.add(product);
		}
		jsonResponse = toJsonProduct(products);
	}

	@Test
	public void queryAll() throws Exception {
		init();
		mvc.perform(get("/query-all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonResponse));

	}
}
