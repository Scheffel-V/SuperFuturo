package com.ufrgs.superfuturo.model;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ufrgs.superfuturo.client.FrontendClient;


@Component
public class Stock {
	private static List<Product> stockedProducts;
		
	private static FrontendClient frontendClient = new FrontendClient();
	private static List<BuyOrder> buyOrders = new ArrayList<BuyOrder>();
	private static List<ReturnOrder> returnOrders = new ArrayList<ReturnOrder>();
	
	public static List<Product> getStockedProducts() {
		return Stock.stockedProducts;
	}
	
	public static void initializeProducts(final List<Product> productsList) {
		Stock.stockedProducts = productsList;
		Stock.sendStockedProducts(productsList);
	}
	
	public static Product getProductByName(final String productName) {
		if (productName == null) {
			return null;
		}
		
		return Stock.stockedProducts
				.stream()
				.filter(p -> p.getName().equals(productName))
				.findFirst()
				.get();
	}
	
	public static Product getProductByInputName(final String productInputName) {
		if (productInputName == null) {
			return null;
		}
		
		return Stock.stockedProducts
				.stream()
				.filter(p -> p.getInputName().equals(productInputName))
				.findFirst()
				.orElse(null);
	}
	
	public static boolean buyProduct(final User user, final Product product) {
		if (!Stock.checkConstraints(user, product)) {
			return false;
		}
		
		if (Stock.isProductInStock(product)) {
			Product productToBuy = Stock.stockedProducts
					.stream()
	                .filter(p -> p.getName().equals(product.getName()))
	                .findFirst()
	                .get();
			
			Stock.stockedProducts.remove(productToBuy);
			Stock.createBuyOrder(user, product);

			try {
				Stock.frontendClient.sendBuyProduct(product);
			} catch (final URISyntaxException ex) {
				ex.printStackTrace();
				System.out.println("FATAL ERROR: error while building URI, request won't be transmitted to the backend");
			}

			return true;
		}
		
		return false;
	}
	
	public static boolean buyProduct(final User user, final String productInputName) {
		return Stock.buyProduct(user, Stock.getProductByInputName(productInputName));
	}
	
	public static boolean returnProduct(final User user, final Product product) {
		if (!Stock.checkConstraints(user, product)) {
			return false;
		}
		
		Stock.stockedProducts.add(product);
		Stock.createReturnOrder(user, product);

		try {
			Stock.frontendClient.sendReturnProduct(product);
		} catch (final URISyntaxException ex) {
			ex.printStackTrace();
			System.out.println("FATAL ERROR: error while building URI, request won't be transmitted to the backend");
		}

		return true;
	}
	
	public static boolean returnProduct(final User user, final String productInputName) {
		return Stock.returnProduct(user, Stock.getProductByInputName(productInputName));
	}
	
	public static boolean isProductInStock(final Product product) {
		if (!Stock.checkConstraints(product)) {
			return false;
		}
		
		return Stock.stockedProducts
				.stream()
				.map(Product::getName)
				.anyMatch(product.getName()::equals);
	}
	
	private static void sendStockedProducts(final List<Product> stockedProductList) {
		final StockProducts stockProducts = new StockProducts(stockedProductList);
		Stock.frontendClient.sendStockProducts(stockProducts);
	}
	
	private static void createBuyOrder(final User user, final Product product) {
		final BuyOrder newBuyOrder = new BuyOrder(product.getName(), user.getCpf(), 1, product.getPrice()); 
		Stock.buyOrders.add(newBuyOrder);
	}
	
	private static void createReturnOrder(final User user, final Product product) {
		final ReturnOrder newReturnOrder = new ReturnOrder(product.getName(), user.getCpf(), 1, product.getPrice()); 
		Stock.returnOrders.add(newReturnOrder);
	}
	
	private static boolean checkConstraints(final Product product) {
		if (product == null) {
			return false;
		}
		
		return true;
	}
	
	private static boolean checkConstraints(final User user, final Product product) {
		if (user == null || product == null) {
			return false;
		}
		
		return true;
	}
}
