package payment.strategy;

import payment.product.Payment;

public interface PaymentStrategy {

	boolean process(Payment payment);

}