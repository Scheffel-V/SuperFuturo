package com.ufrgs.superfuturo.model;

import java.lang.reflect.MalformedParametersException;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String cpf;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditcard_id", referencedColumnName = "id")
	private CreditCard creditCard;
	
	public User() {
		
	}
	
	public User(final long id, final String cpf, final CreditCard creditCard) throws IllegalArgumentException {
		super();
		this.setId(id);
		this.setCpf(cpf);
		this.setCreditCard(creditCard);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public void setCpf(final String cpf) throws IllegalArgumentException {
		if (!isValidCPF(cpf)) {
			throw new IllegalArgumentException("Invalid CPF " + cpf);
		}

		this.cpf = cpf;
	}
	
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public String toString() {
		return "ID:" + this.getId().toString() + "CPF:" + this.getCpf().toString();
	}


	// expects a string of numbers, for example 546.471.429-49 would be passed as 54647142949
	private static boolean isValidCPF(String cpf) throws MalformedParametersException {
		final Pattern cpfRegex = Pattern.compile("[0-9]{11}");

		if(!cpfRegex.matcher(cpf).matches()) {
			throw new MalformedParametersException("CPF must be provided as a strings of numbers, without dots or dashes.");
		}

		// these cases are valid (digit-check wise), but are not considered valid cpfs
		if (cpf.equals("00000000000") ||
						cpf.equals("11111111111") ||
						cpf.equals("22222222222") || cpf.equals("33333333333") ||
						cpf.equals("44444444444") || cpf.equals("55555555555") ||
						cpf.equals("66666666666") || cpf.equals("77777777777") ||
						cpf.equals("88888888888") || cpf.equals("99999999999") ||
						(cpf.length() != 11))
			return false;

		int accumulator = 0;
		int multiplier = 10;

		for (int i=0; i < 9; i++) {
			accumulator += multiplier * Character.getNumericValue(cpf.charAt(i));
			multiplier--;
		}

		final int dvRest10 = 11 - (accumulator % 11);
		final int digit10 = (dvRest10 == 10) || (dvRest10 == 11) ? 0 : dvRest10;

		if (digit10 != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		accumulator = 0;
		multiplier = 11;

		for (int i=0; i < 10; i++) {
			accumulator += multiplier * Character.getNumericValue(cpf.charAt(i));
			multiplier--;
		}

		final int dvRest11 = 11 - (accumulator % 11);
		final int digit11 = (dvRest11 == 10) || (dvRest11 == 11) ? 0 : dvRest11;

		if (digit11 != Character.getNumericValue(cpf.charAt(10))) {
			return false;
		}

		return true;
	}
	
	public void buyProduct(final Product product) {
		Stock.buyProduct(this, product);
	}
	
	public void returnProduct(final Product product) {
		Stock.returnProduct(this, product);
	}
}
