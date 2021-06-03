package entity.payment;

public class DomesticCard extends Card {
	private String cardType; 
	private String issuringbank; 
	private String cardNumber;
    private String cardholderName;
    private String validFromDate;
    
    public DomesticCard(String cardType, String issuringbank, String cardNumber, String cardholderName,
			String validFromDate) {
		super();
		this.cardType = cardType;
		this.issuringbank = issuringbank;
		this.cardNumber = cardNumber;
		this.cardholderName = cardholderName;
		this.validFromDate = validFromDate;
	}
}
