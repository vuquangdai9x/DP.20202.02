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
import entity.payment.DomesticCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import subsystem.interbank.InterbankSubsystemCreditCardController;
import subsystem.interbank.InterbankSubsystemDomesticCardController;
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
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link String String} represents the input date
	 * @return {@link String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	/**
	 * <p>Vu Quang Dai</p>
	 * <p>Coicidental Cohesion</p>
	 * <p>String date validation is not functional relate to payment. This method should be place in an utility class</p>
	 */
//	private String getExpirationDate(String date) throws InvalidCardException {
//		String[] strs = date.split("/");
//		if (strs.length != 2) {
//			throw new InvalidCardException();
//		}
//
//		String expirationDate = null;
//		int month = -1;
//		int year = -1;
//
//		try {
//			month = Integer.parseInt(strs[0]);
//			year = Integer.parseInt(strs[1]);
//			if (validateDate(month, year)) {
//				throw new InvalidCardException();
//			}
//			expirationDate = strs[0] + strs[1];
//
//		} catch (Exception ex) {
//			throw new InvalidCardException();
//		}
//
//		return expirationDate;
//	}
//	
//	private boolean validateDate(int month, int year) {
//		return month < 1 || month > 12 || 
//				month < Calendar.getInstance().get(Calendar.YEAR) % 100 || 
//				year > 100;
//	}

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
	
	public Map<String, String> payOrder(int amount, String contents, String cardType, String issuringbank, 
			String cardNumber, String cardholderName, String validFromDate) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		
		Validate validate = new Validate();
		
		try {
			this.card = new DomesticCard(cardType, issuringbank, cardNumber, cardholderName, validFromDate);

			if (this.interbank == null) this.interbank = new InterbankSubsystem();
			this.interbank.setPaymentController(new InterbankSubsystemDomesticCardController());
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