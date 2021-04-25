package kaan.demo.warehouse.repo;

import org.springframework.data.repository.CrudRepository;

import kaan.demo.warehouse.model.ProductArticle;

public interface ProductArticleRepo extends CrudRepository<ProductArticle, Integer> {

}
