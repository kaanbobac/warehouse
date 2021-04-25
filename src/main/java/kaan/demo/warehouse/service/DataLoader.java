package kaan.demo.warehouse.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kaan.demo.warehouse.dto.InventoryDto;
import kaan.demo.warehouse.dto.JsonDto;
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

	public <T> String saveDataFromJson(JsonDto params) {
		String result = "";
		String path = params.getPath();
		if (params.getModel().equalsIgnoreCase(CONST_DATA_INVENTORY)) {
			result = saveInventory(path);
		}
		return result;
	}

	private <T> String saveInventory(String path) {
		String result;
		TypeReference<InventoryDto> typeReference = new TypeReference<InventoryDto>() {
		};
		T parsed = parseJson(path, typeReference);
		if (parsed == null)
			result = "Fail";
		else {
			InventoryDto dto = (InventoryDto) parsed;
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
