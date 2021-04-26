package kaan.demo.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.warehouse.dto.ProductDto;
import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public void saveAll(List<Product> products) {
		repo.saveAll(products);
	}

	public List<ProductDto> queryAll() {
		List<Product> products = (List<Product>) repo.findAll();
		List<ProductDto> dto = new ArrayList<>();
		for (Product p : products) {
			dto.add(convertToDto(p));
		}
		return dto;
	}

	private ProductDto convertToDto(Product product) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(product, ProductDto.class);
	}
}
