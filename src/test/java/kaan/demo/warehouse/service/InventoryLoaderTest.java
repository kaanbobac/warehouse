package kaan.demo.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.fasterxml.jackson.core.type.TypeReference;

import kaan.demo.warehouse.model.Article;
import kaan.demo.warehouse.model.InventoryWrapper;

public class InventoryLoaderTest {
	@InjectMocks
	private DataLoader dataLoader = new DataLoader();;
	private static final String path_ten = "/test/json/ten-inventory.json";
	private static final String path_one = "/test/json/one-inventory.json";
	private static final String path_zero = "/test/json/empty-inventory.json";
	private static List<Article> expectation_ten = new ArrayList<Article>();
	private static List<Article> expectation_one = new ArrayList<Article>();

	@BeforeAll
	public static void init() {
		expectation_ten = Arrays.asList(new Article(1, "leg", 12), new Article(2, "screw", 17),
				new Article(3, "seat", 2), new Article(4, "table top", 1), new Article(5, "five", 18),
				new Article(6, "six", 34), new Article(7, "seven", 0), new Article(8, "eight", 44),
				new Article(9, "nine", 100), new Article(10, "ten", 33));
		expectation_one = Arrays.asList(new Article(1, "leg", 12));

	}

	@DisplayName("Inventory Json Parse with 10 entites")
	@Test
	public <T> void parse_inventory_success_ten_inventory() {
		boolean result = true;
		InventoryWrapper dto;
		List<Article> actual = new ArrayList<>();
		TypeReference<InventoryWrapper> typeReference = new TypeReference<InventoryWrapper>() {
		};
		T parsed = dataLoader.parseJson(path_ten, typeReference);
		if (parsed == null)
			result = false;
		else {
			dto = (InventoryWrapper) parsed;
			actual = dto.getInventory();
		}
		for (int i = 0; i < expectation_ten.size(); i++) {
			if (!actual.get(i).toString().equalsIgnoreCase(expectation_ten.get(i).toString()))
				result = false;
		}
		assertTrue(result);
	}

	@DisplayName("Inventory Json Parse with 1 entites")
	@Test
	public <T> void parse_inventory_success_one_inventory() {
		boolean result = true;
		InventoryWrapper dto;
		List<Article> actual = new ArrayList<>();
		TypeReference<InventoryWrapper> typeReference = new TypeReference<InventoryWrapper>() {
		};
		T parsed = dataLoader.parseJson(path_one, typeReference);
		if (parsed == null)
			result = false;
		else {
			dto = (InventoryWrapper) parsed;
			actual = dto.getInventory();
		}
		for (int i = 0; i < expectation_one.size(); i++) {
			if (!actual.get(i).toString().equalsIgnoreCase(expectation_one.get(i).toString()))
				result = false;
		}
		assertTrue(result);
	}

	@DisplayName("Inventory Json Parse with 0 entites")
	@Test
	public <T> void parse_inventory_success_zero_inventory() {
		boolean result = true;
		InventoryWrapper dto;
		List<Article> actual = new ArrayList<>();
		TypeReference<InventoryWrapper> typeReference = new TypeReference<InventoryWrapper>() {
		};
		T parsed = dataLoader.parseJson(path_ten, typeReference);
		if (parsed == null)
			result = false;
		else {
			dto = (InventoryWrapper) parsed;
			actual = dto.getInventory();
		}
		if (actual.size() == 0)
			result = false;
		assertTrue(result);

	}
}
