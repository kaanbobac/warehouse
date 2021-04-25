package kaan.demo.warehouse.repo;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.repository.CrudRepository;

import kaan.demo.warehouse.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
