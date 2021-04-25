package kaan.demo.warehouse.dto;

import java.util.List;

import kaan.demo.warehouse.model.Article;
import lombok.Data;

@Data
public class InventoryDto {
	private List<Article> inventory;
}
