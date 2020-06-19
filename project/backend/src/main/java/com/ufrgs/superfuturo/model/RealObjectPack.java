package com.ufrgs.superfuturo.model;

public class RealObjectPack {
	private String name;
	private int quantity;
	
	public RealObjectPack(final String name, final int quantity) {
		this.setName(name);
		this.setQuantity(quantity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
