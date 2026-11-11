package payment.state;

import entities.enums.OrderStatus;
import payment.product.Payment;
import payment.strategy.RefundStrategy;

public interface PaymentState {

	PaymentState complete();

	PaymentState cancelOrRefund(Payment payment, RefundStrategy refundStrategy);

	boolean canBeCancelledOrRefunded();

	boolean isConsistentWith(OrderStatus orderStatus);

	PaymentStatus getStatus();

}
