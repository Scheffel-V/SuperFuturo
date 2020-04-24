package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private CreditCard creditCard;
	
	public User() {
		
	}
	
	public User(final long id, final String cpf, final CreditCard creditCard) {
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
	
	public void setCpf(final String cpf) {
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
}
