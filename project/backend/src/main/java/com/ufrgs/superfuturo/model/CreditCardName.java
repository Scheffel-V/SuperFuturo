package com.ufrgs.superfuturo.model;

public enum CreditCardName {
	MASTERCARD("MASTERCARD"), VISA("VISA");
	
	private String name;
	
	CreditCardName(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
