package kaan.demo.warehouse.e2e;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kaan.demo.warehouse.dto.ProductJsonDto;

public class BaseTest {
	protected String toJsonProduct(List<ProductJsonDto> products) {
		String result = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(products);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
