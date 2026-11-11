package payment.factory;

import entities.Order;
import payment.product.InternationalPayment;
import payment.product.Payment;

public class InternationalPaymentFactory extends PaymentFactory {

	@Override
	protected Payment instantiatePayment(String paymentCode, Order order, Double amount) {

		return new InternationalPayment(paymentCode, order, amount);
	}
}
