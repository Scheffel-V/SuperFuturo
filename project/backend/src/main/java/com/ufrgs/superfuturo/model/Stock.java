package com.ufrgs.superfuturo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufrgs.superfuturo.service.BuyOrderService;
import com.ufrgs.superfuturo.service.ReturnOrderService;

public class Stock {
	private static List<Product> stockedProducts;
	
	@Autowired
	private static BuyOrderService buyOrderService;
	
	@Autowired
	private static ReturnOrderService returnOrderService;

	public static List<Product> getStockedProducts() {
		return stockedProducts;
	}

	public static void setStockedProducts(final List<Product> stockedProducts) {
		Stock.stockedProducts = stockedProducts;
	}
	
	public static Product getProductByName(final String productName) {
		return Stock.stockedProducts
				.stream()
				.filter(p -> p.getName().equals(productName))
				.findFirst()
				.get();
	}
	
	public static Product getProductByInputName(final String productInputName) {
		return Stock.stockedProducts
				.stream()
				.filter(p -> p.getInputName().equals(productInputName))
				.findFirst()
				.get();
	}
	
	public static void buyProduct(final User user, final Product product) {
		if (Stock.isProductInStock(product)) {
			Product productToBuy = Stock.stockedProducts
					.stream()
	                .filter(p -> p.getName().equals(product.getName()))
	                .findFirst()
	                .get();
			
			Stock.stockedProducts.remove(productToBuy);
			
			Stock.createBuyOrder(user, product);
		}
	}
	
	public static void returnProduct(final User user, final Product product) {
		Stock.stockedProducts.add(product);
		Stock.createReturnOrder(user, product);
	}
	
	private static boolean isProductInStock(final Product product) {
		return Stock.stockedProducts
				.stream()
				.map(Product::getName)
				.anyMatch(product.getName()::equals);
	}
	
	private static void createBuyOrder(final User user, final Product product) {
		final BuyOrder newBuyOrder = new BuyOrder(product.getName(), user.getCpf(), 1, product.getPrice()); 
		Stock.buyOrderService.addBuyOrder(newBuyOrder);
	}
	
	private static void createReturnOrder(final User user, final Product product) {
		final ReturnOrder newReturnOrder = new ReturnOrder(product.getName(), user.getCpf(), 1, product.getPrice()); 
		Stock.returnOrderService.addReturnOrder(newReturnOrder);
	}
}
