package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "creditcards")
public class CreditCard {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String cardNumber;
	
	@NotNull
	private CreditCardName creditCardName;
	
	@NotNull
	private Date validThru;
	
	@NotNull
	@Size(max = 5)
	private String securityCode;
	
	public CreditCard() {
		
	}
	
	public CreditCard(final Long id, final String cardNumber, final CreditCardName creditCardName, final Date validThru, final String securityCode) {
		this.id = id;
		this.setCardNumber(cardNumber);
		this.setCreditCardName(creditCardName);
		this.setValidThru(validThru);
		this.setSecurityCode(securityCode);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public CreditCardName getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(CreditCardName creditCardName) {
		this.creditCardName = creditCardName;
	}

	public Date getValidThru() {
		return validThru;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}	
}
