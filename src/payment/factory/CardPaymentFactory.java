package payment.factory;

import entities.Order;
import payment.product.CardPayment;
import payment.product.Payment;

public class CardPaymentFactory extends PaymentFactory {

	@Override
	public Payment createPayment(String paymentCode, Order order, Double amount, String currency) {

		return new CardPayment(paymentCode, order, amount, currency);
	}
}