package payment.factory;

import entities.Order;
import payment.product.Payment;
import payment.product.PixPayment;

public class PixPaymentFactory extends PaymentFactory {

	@Override
	public Payment createPayment(String paymentCode, Order order, Double amount, String currency) {

		return new PixPayment(paymentCode, order, amount, currency);
	}
}