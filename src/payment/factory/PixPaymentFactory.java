package payment.factory;

import entities.Order;
import payment.product.PixPayment;
import payment.product.Payment;

public class PixPaymentFactory extends PaymentFactory {

	@Override
	protected Payment instantiatePayment(String paymentCode, Order order, Double amount) {

		return new PixPayment(paymentCode, order, amount);
	}
}
