package kaan.demo.warehouse.service.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Pojo Class for Testing Json Parser
 * @author Kaan Bobac
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonParsePojo {
	private Integer id;
	private String field_string;
	private Integer field_int;
}
