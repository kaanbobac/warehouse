package kaan.demo.warehouse.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import kaan.demo.warehouse.dto.ProductJsonDto;
import kaan.demo.warehouse.repo.ProductRepository;

@SpringBootTest(properties = { "app.data.inventory.json.path=/test/json/test06-inventory.json",
		"app.data.product.json.path=/test/json/test06-products.json" })
@AutoConfigureMockMvc
public class QueryAllProductsNoAvailableTest extends BaseTest {
	@Autowired
	private MockMvc mvc;
	private static List<ProductJsonDto> products = new ArrayList<>();
	private static String jsonResponse;
	@Autowired
	private ProductRepository productRepo;

	public void init() {
		jsonResponse = toJsonProduct(new ArrayList<>());
	}

	@DisplayName("Product Json includes no valid Article")
	@Test
	public void queryAll() throws Exception {
		init();
		mvc.perform(get("/query-all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonResponse));

	}
}
