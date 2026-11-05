package payment.factory;

import entities.Order;
import payment.product.InternationalPayment;
import payment.product.Payment;

public class InternationalPaymentFactory extends PaymentFactory {

	@Override
	public Payment createPayment(String paymentCode, Order order, Double amount, String currency) {

		return new InternationalPayment(paymentCode, order, amount, currency);
	}
}