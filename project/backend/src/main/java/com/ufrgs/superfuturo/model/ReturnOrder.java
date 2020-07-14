package com.ufrgs.superfuturo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "returnorders")
public class ReturnOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String productName;
	
	@NotNull
	private String userCpf;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private double price;
	
	public ReturnOrder() {
		
	}
	
	public ReturnOrder(final String productName, final String userCpf, final int quantity, final double price) {
		this.productName = productName;
		this.userCpf = userCpf;
		this.quantity = quantity;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}
	
	public String getUserCpf() {
		return this.userCpf;
	}
	
	public void setUserCpf(final String userCpf) {
		this.userCpf = userCpf;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}
}
