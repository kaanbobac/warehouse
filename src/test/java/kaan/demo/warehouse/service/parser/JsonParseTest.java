package kaan.demo.warehouse.service.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.fasterxml.jackson.core.type.TypeReference;
import kaan.demo.warehouse.service.DataLoader;

public class JsonParseTest {
	@InjectMocks
    private DataLoader dataLoader= new DataLoader();
	private static final String TEST_PATH = "/test/json/parse-test.json";
	private static List<JsonParsePojo> expectation = new ArrayList<JsonParsePojo>();
	@BeforeAll
    public static void init() {
		expectation = Arrays.asList(
	                new JsonParsePojo(1,"xxx",11),
	                new JsonParsePojo(2,"yyy",12)
	    );		 
    }
	@DisplayName("Json Parse Test with Two Entites")
	@Test
	public <T> void parse_inventory_success_ten_inventory() {
		boolean result = true;
		JsonParseDto dto;
		List<JsonParsePojo> actual = new ArrayList<>();
		TypeReference<JsonParseDto> typeReference = new TypeReference<JsonParseDto>(){};
		T parsed = dataLoader.parseJson(TEST_PATH,typeReference);
		if(parsed == null)
			result = false;
		else {
			dto = (JsonParseDto) parsed;
			actual = dto.getMyObjectArray();
		}
		for(int i = 0; i< expectation.size() ; i ++) {
			if(!actual.get(i).toString().
					equalsIgnoreCase(expectation.get(i).toString()))
				result = false;
		}
		assertTrue(result);
	}
}
