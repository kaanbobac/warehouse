package kaan.demo.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Article Entity as median between Article and Product Table Please
 * check ReadMe file for database relation
 * 
 * @author Kaan Bobac
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_ARTICLES")
public class ProductArticle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ARTICLE_ID")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "ART_ID", referencedColumnName = "ARTICLE_ID")
	private Article article;
	@Column(name = "AMOUNT_NEEDED")
	private Integer amount_of;
	@Transient
	private Integer art_id;
}
