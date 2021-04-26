package kaan.demo.warehouse.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kaan.demo.warehouse.dto.JsonDto;
import kaan.demo.warehouse.dto.ProductJsonDto;
import kaan.demo.warehouse.model.InventoryWrapper;
import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.model.ProductArticle;
import lombok.extern.java.Log;

/**
 * Component Class for parsing json and store data
 * 
 * @author Kaan Bobac
 *
 */
@Log
@Component
public class DataLoader {
	private static final String CONST_DATA_INVENTORY = "inventory";
	private static final String CONST_DATA_PRODUCT = "products";
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductArticleService productArticleService;

	@EventListener(ApplicationReadyEvent.class)
	public void initDatabase() {
		saveInventory("/json/inventory.json");
		saveProduct("/json/products.json");
	}

	public <T> String saveDataFromJson(JsonDto params) {
		String result = "";
		String path = params.getPath();
		if (params.getModel().equalsIgnoreCase(CONST_DATA_INVENTORY)) {
			result = saveInventory(path);
		} else if (params.getModel().equalsIgnoreCase(CONST_DATA_PRODUCT)) {
			result = saveProduct(path);
		}
		return result;
	}

	private <T> String saveProduct(String path) {
		String result;
		TypeReference<ProductJsonDto> typeReference = new TypeReference<ProductJsonDto>() {
		};
		T parsed = parseJson(path, typeReference);
		if (parsed == null)
			result = "Fail";
		else {
			ProductJsonDto dto = (ProductJsonDto) parsed;
			List<Product> products = dto.getProducts();
			for (Product p : products) {
				List<ProductArticle> productArticles = p.getContain_articles();
				for (ProductArticle productArticle : productArticles) {
					productArticle.setArticle(inventoryService.findOne(productArticle.getArt_id()));
					productArticleService.saveOne(productArticle);
				}
			}
			productService.saveAll(products);
		}
		return "OK";
	}

	private <T> String saveInventory(String path) {
		String result;
		TypeReference<InventoryWrapper> typeReference = new TypeReference<InventoryWrapper>() {
		};
		T parsed = parseJson(path, typeReference);
		if (parsed == null)
			result = "Fail";
		else {
			InventoryWrapper dto = (InventoryWrapper) parsed;
			inventoryService.saveAll(dto.getInventory());
			result = "Success";
		}
		return result;
	}

	public <T> T parseJson(String jsonPath, TypeReference typeReference) {
		T result = null;
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPath);
		log.info("Started parsing Json --->" + jsonPath);
		try {
			result = (T) mapper.readValue(inputStream, typeReference);
		} catch (Exception e) {
			log.severe("[ERROR] Unable to parse JSon" + e.getMessage());
		}
		log.info("Json parse has been completed");
		return result;
	}
}
