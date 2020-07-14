package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.Product;
import com.ufrgs.superfuturo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void addPerson(final Product product) {
		this.productRepository.save(product);
	}

	public List<Product> getAllProduct() {
		final List<Product> products = new ArrayList<Product>();
		this.productRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	public void addProduct(final List<Product> products) {
		this.productRepository.saveAll(products);
	}
	
	public void removeAllProduct() {
		this.productRepository.deleteAll();
	}
}
