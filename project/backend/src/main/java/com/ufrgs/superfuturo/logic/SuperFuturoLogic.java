package com.ufrgs.superfuturo.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufrgs.superfuturo.model.CreditCard;
import com.ufrgs.superfuturo.model.CreditCardName;
import com.ufrgs.superfuturo.model.Product;
import com.ufrgs.superfuturo.model.Stock;
import com.ufrgs.superfuturo.model.User;


public abstract class SuperFuturoLogic {
	
	private static boolean started = false;
	private static User user = new User(0L, "82121262776", new CreditCard(0L, "1234.1234.1234.1234", CreditCardName.MASTERCARD, new Date(), "123"));
	
	public static void userBuyProduct(final String productInputName) {
		SuperFuturoLogic.user.buyProduct(productInputName);
	}
	
	public static void userReturnProduct(final String productInputName) {
		SuperFuturoLogic.user.returnProduct(productInputName);
	}
	
	public static void initializeStockProducts(final List<Product> stockProducts) {
		Stock.initializeProducts(stockProducts);
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SuperFuturoLogic.user = user;
	}
	
	public static void start() {
		if (!SuperFuturoLogic.started) {
			
			final Product p1 = new Product("Visconti", "visconti", 10.50);
			final Product p2 = new Product("Ades", "ades", 5);
			final Product p3 = new Product("Italac", "italac", 6);
			final Product p4 = new Product("Visconti", "visconti", 10.50);
			final Product p5 = new Product("Ades", "ades", 5);
			final Product p6 = new Product("Italac", "italac", 6);
			
			final List<Product> products = new ArrayList<Product>();
			
			products.add(p1);
			products.add(p2);
			products.add(p3);
			products.add(p4);
			products.add(p5);
			products.add(p6);
			
			SuperFuturoLogic.initializeStockProducts(products);
		}
	}
}
