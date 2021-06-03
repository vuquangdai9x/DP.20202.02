package subsystem.interbank;

import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankSubsystemControllerInterface {
	PaymentTransaction refund(Card card, int amount, String contents);
	PaymentTransaction payOrder(Card card, int amount, String contents);
}
