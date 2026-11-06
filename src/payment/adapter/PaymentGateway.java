package payment.adapter;

import payment.product.Payment;

public interface PaymentGateway {

	boolean processPayment(Payment payment);

	boolean refundPayment(Payment payment);

}