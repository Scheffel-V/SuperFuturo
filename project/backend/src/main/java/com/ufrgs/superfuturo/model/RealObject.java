package com.ufrgs.superfuturo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "realobjects")
public class RealObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	private String name;
	
	private int probability;
	
	public RealObject() {
		
	}
	
	public RealObject(final String name) {
		this.setName(name);
		this.setProbability(0);
	}
	
	public RealObject(final String name, final int probability) {
		this.setName(name);
		this.setProbability(probability);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public int getProbability() {
		return probability;
	}

	public void setProbability(final int probability) {
		this.probability = probability;
	}
}
