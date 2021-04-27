package kaan.demo.warehouse.dto;

import lombok.Data;

/**
 * Dto Class for mapping Product Model to JSON Response
 * 
 * @author Kaan Bobac
 *
 */
@Data
public class ProductJsonDto {
	private Integer id;
	private String name;
	private int quantity;
}
