package payment.adapter;

import payment.product.Payment;

public interface PaymentGateway {

	boolean processPayment(Payment payment);

}