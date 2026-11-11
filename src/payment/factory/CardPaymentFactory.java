package payment.factory;

import entities.Order;
import payment.product.CardPayment;
import payment.product.Payment;

public class CardPaymentFactory extends PaymentFactory {

	@Override
	protected Payment instantiatePayment(String paymentCode, Order order, Double amount) {

		return new CardPayment(paymentCode, order, amount);
	}
}
