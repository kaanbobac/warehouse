package kaan.demo.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * Inventory Entity for storing article ids with stock amounts
 * Please check ReadMe file for database relation
 * @author Kaan Bobac
 *
 */
@Data
@Entity
@Table(name = "INVENTORY")
public class Article {
	@Id
	@Column(name = "ARTICLE_ID")
    private Integer art_id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "STOCK_COUNT")
	private Integer stock;
}
