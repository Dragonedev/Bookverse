package cancellation;

import payment.product.Payment;
import repository.OrderRepository;
import repository.PaymentRepository;

public class OrderCancellationService {

	private final PaymentRepository paymentRepository;

	private final OrderCancellationHandler handlerChain;

	public OrderCancellationService(OrderRepository orderRepository, PaymentRepository paymentRepository) {

		this.paymentRepository = paymentRepository;

		OrderCancellationHandler orderExists = new OrderExistsHandler(orderRepository);

		OrderCancellationHandler paymentExists = new PaymentExistsHandler(paymentRepository);

		OrderCancellationHandler paymentOwnership = new PaymentOwnershipHandler();

		OrderCancellationHandler paymentStatus = new PaymentStatusHandler();

		orderExists.setNext(paymentExists).setNext(paymentOwnership).setNext(paymentStatus);

		this.handlerChain = orderExists;
	}

	public Payment cancelOrder(String orderCode, String paymentCode) {

		CancellationContext context = new CancellationContext(orderCode, paymentCode);

		handlerChain.handle(context);

		Payment payment = context.getPayment();

		if ("PENDING".equals(payment.getStatus())) {
			payment.cancelPayment();
		} else {
			payment.refundPayment();
		}

		paymentRepository.save(payment);

		return payment;
	}
}