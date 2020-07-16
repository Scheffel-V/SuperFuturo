package com.ufrgs.superfuturo.model;

import java.util.ArrayList;
import java.util.List;

public class StockProducts {
	
	private List<OutputObject> produtos;
	
	public StockProducts() {
		this.produtos = new ArrayList<OutputObject>();
	}
		
	public StockProducts(final List<Product> produtos) {
		this.produtos = new ArrayList<OutputObject>();
		this.setProdutos(produtos);
	}

	public List<OutputObject> getProdutos() {
		return produtos;
	}

	public void setProdutos(final List<Product> produtos) {
		for (Product product : produtos) {
			this.addProduct(product);
		}
	}
	
	public void addProduct(final Product product) {
		this.produtos.add(new OutputObject("0", product.getName(), "1", Double.toString(product.getPrice())));
	}
}
