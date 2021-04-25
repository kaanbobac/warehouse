package kaan.demo.warehouse.repo;

import org.springframework.data.repository.CrudRepository;

import kaan.demo.warehouse.model.Article;

public interface InventoryRepository extends CrudRepository<Article, Integer> {

}
