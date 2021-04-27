package kaan.demo.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.warehouse.dto.ProductJsonDto;
import kaan.demo.warehouse.model.Article;
import kaan.demo.warehouse.model.Product;
import kaan.demo.warehouse.model.ProductArticle;
import kaan.demo.warehouse.repo.InventoryRepository;
import kaan.demo.warehouse.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	InventoryRepository articleRepo;

	public void saveAll(List<Product> products) {
		productRepo.saveAll(products);
	}

	public void save(Product product) {
		productRepo.save(product);
	}

	public List<ProductJsonDto> sellProduct(int id) {
		updateInventory(id);
		return queryAll();
	}

	private void updateInventory(int id) {
		Product removingProduct = productRepo.findById(id).orElse(null);
		List<ProductArticle> removingProductArticle = removingProduct.getContain_articles();
		for (ProductArticle productArticle : removingProductArticle) {
			Article a = productArticle.getArticle();
			a.setStock(a.getStock() - productArticle.getAmount_of());
			articleRepo.save(a);
		}
	}

	public List<ProductJsonDto> queryAll() {
		List<Product> products = (List<Product>) productRepo.findAll();
		List<ProductJsonDto> dto = new ArrayList<>();
		for (Product p : products) {
			dto.add(convertToDto(p));
		}
		return dto;
	}

	private ProductJsonDto convertToDto(Product product) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(product, ProductJsonDto.class);
	}
}
