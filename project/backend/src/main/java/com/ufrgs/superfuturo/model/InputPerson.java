package com.ufrgs.superfuturo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "persons")
public class InputPerson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	private String classe;
	
	@NotNull
	private int prob;
	
	@NotNull
	private double bx;
	
	@NotNull
	private double by;
	
	@NotNull
	private double bw;
	
	@NotNull
	private double bh;
	
	@NotNull
	private Date timestamp;
	
	public InputPerson() {
		
	}
	
	public InputPerson(final String classe, final int prob, final double bx, final double by, final double bw, final double bh, final Date timestamp) {
		this.classe = classe;
		this.prob = prob;
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
		this.timestamp = timestamp;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public String getClasse() {
		return this.classe;
	}
	
	public void setClasse(final String classe) {
		this.classe = classe;
	}
	
	public double getProb() {
		return this.prob;
	}
	
	public void setProb(final int prob) {
		this.prob = prob;
	}
	
	public double getBx() {
		return this.bx;
	}
	
	public void setBx(final int bx) {
		this.bx = bx;
	}

	public double getBy() {
		return this.by;
	}
	
	public void getBy(final int by) {
		this.by = by;
	}
	
	public double getBw() {
		return this.bw;
	}
	
	public void setBw(final int bw) {
		this.bw = bw;
	}
	
	public double getBh() {
		return this.bh;
	}
	
	public void setBh(final int bh) {
		this.bh = bh;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}
}
