package cancellation;

import entities.Order;
import payment.product.Payment;

public class CancellationContext {

	private final String orderCode;
	private final String paymentCode;

	private Order order;
	private Payment payment;

	public CancellationContext(String orderCode, String paymentCode) {
		this.orderCode = orderCode;
		this.paymentCode = paymentCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public Order getOrder() {
		return order;
	}

	public Payment getPayment() {
		return payment;
	}

	void setOrder(Order order) {
		this.order = order;
	}

	void setPayment(Payment payment) {
		this.payment = payment;
	}
}
