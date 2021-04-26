package kaan.demo.warehouse.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * Product Entity for storing Product Please check ReadMe file for database
 * relation
 * 
 * @author Kaan Bobac
 *
 */
@Data
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private List<ProductArticle> contain_articles;
	@Column(name = "NAME")
	private String name;
	@Transient
	@Getter(AccessLevel.NONE)
	private int quantity;

	private void calculateQuantity() {
		List<Integer> quantities = contain_articles.stream().map((productArticle) -> productArticle.getQuantity())
				.collect(Collectors.toList());
		Collections.sort(quantities);
		setQuantity(quantities.get(0));
	}

	public int getQuantity() {
		calculateQuantity();
		return quantity;
	}
}
