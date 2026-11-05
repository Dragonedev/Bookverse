package payment.factory;

import entities.Order;
import payment.product.Payment;

public abstract class PaymentFactory {

	public abstract Payment createPayment(String paymentCode, Order order, Double amount, String currency);
}