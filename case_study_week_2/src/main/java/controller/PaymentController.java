package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import entity.payment.Card;
import entity.payment.CreditCard;
//import entity.payment.DomesticCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import subsystem.interbank.InterbankSubsystemCreditCardController;
//import subsystem.interbank.InterbankSubsystemDomesticCardController;
import utils.Validate;

/**
 * Le Minh Duc
 * Coincidental Cohesion
 * Cac method trong class khong co su lien quan voi nhau
 */

 /**
 * Le Minh Duc
 * SOLID: Vi pháº¡m nguyÃªn lÃ½ OCR
 * PaymentController má»›i chá»‰ cÃ³ phÆ°Æ¡ng thá»©c thanh toÃ¡n vá»›i payOrder, vá»� sau cÃ³ nhiá»�u phÆ°Æ¡ng thá»©c sáº½ pháº£i modify láº¡i mÃ£ nguá»“n PaymentController 
 */

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private Card card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		
		Validate validate = new Validate();
		
		try {
			this.card = new CreditCard(
					cardNumber,
					cardHolderName,
					validate.getExpirationDate(expirationDate),
					Integer.parseInt(securityCode));

			if (this.interbank == null) this.interbank = new InterbankSubsystem();
			this.interbank.setPaymentController(new InterbankSubsystemCreditCardController());
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);

			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	public void emptyCart(){
        SessionInformation.getInstance().cartInstance.emptyCart();
    }
}