package subsystem.interbank;

import entity.payment.Card;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemDomesticCardController implements InterbankSubsystemControllerInterface {
	private static InterbankDomesticPayloadConverter interbankPayloadConverter = new InterbankDomesticPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();
	
	public PaymentTransaction refund(Card card, int amount, String contents) {
		return null;
	}

	public PaymentTransaction payOrder(Card card, int amount, String contents) {
		return null;
	}
}
