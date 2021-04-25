package kaan.demo.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public void saveAll(List<Product> products) {
		repo.saveAll(products);
	}
}
