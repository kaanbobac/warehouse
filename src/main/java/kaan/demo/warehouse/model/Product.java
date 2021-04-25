package kaan.demo.warehouse.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
/**
 * Product Entity for storing Product
 * Please check ReadMe file for database relation
 * @author Kaan Bobac
 *
 */
@Data
@Entity
@Table(name="PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Integer id;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="PRODUCT_ID",referencedColumnName = "ID")
	private List<ProductArticle> contain_articles;
	@Column(name = "NAME")
	private String name;
}
