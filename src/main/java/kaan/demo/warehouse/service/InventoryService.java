package kaan.demo.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaan.demo.warehouse.model.Article;
import kaan.demo.warehouse.repo.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	InventoryRepository repo;

	public void saveAll(List<Article> articles) {
		repo.saveAll(articles);
	}

	public Article findOne(int id) {
		Article a = repo.findById(id).orElse(null);
		return a;
	}
}
