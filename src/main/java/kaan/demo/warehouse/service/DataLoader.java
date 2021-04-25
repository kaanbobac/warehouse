package kaan.demo.warehouse.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

/**
 * Component Class for parsing json and store data
 * @author Kaan Bobac
 *
 */
@Log
@Component
public class DataLoader {
	public <T> T parseJson(String jsonPath, TypeReference typeReference) {
		T  result = null;
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPath);
		log.info("Started parsing Json --->" + jsonPath);
		try {
			result = (T) mapper.readValue(inputStream,typeReference);
		} catch (Exception e){
			log.severe("[ERROR] Unable to parse JSon" + e.getMessage());
		}
		log.info("Json parse has been completed");
		return result;
	}
}
