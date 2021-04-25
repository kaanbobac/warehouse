package kaan.demo.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.warehouse.model.ProductArticle;
import kaan.demo.warehouse.repo.ProductArticleRepo;

@Service
public class ProductArticleService {

	@Autowired
	ProductArticleRepo productArticleRepo;

	public void saveAll(List<ProductArticle> elements) {
		productArticleRepo.saveAll(elements);
	}

	public void saveOne(ProductArticle element) {
		productArticleRepo.save(element);
	}
}
