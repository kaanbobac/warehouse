package kaan.demo.warehouse.repo;

import org.springframework.data.repository.CrudRepository;

import kaan.demo.warehouse.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
