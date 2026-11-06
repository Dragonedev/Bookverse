package payment.strategy;

import payment.product.Payment;

public interface RefundStrategy {

	boolean refund(Payment payment);

}