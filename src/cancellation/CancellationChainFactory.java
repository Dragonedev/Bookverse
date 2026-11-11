package cancellation;

import repository.OrderRepository;
import repository.PaymentRepository;

public final class CancellationChainFactory {

	private CancellationChainFactory() {
	}

	public static OrderCancellationHandler createDefaultChain(OrderRepository orderRepository,
			PaymentRepository paymentRepository) {

		OrderCancellationHandler head = new OrderExistsHandler(orderRepository);

		head.setNext(new PaymentExistsHandler(paymentRepository))
			.setNext(new PaymentOwnershipHandler())
			.setNext(new OrderStatusHandler())
			.setNext(new PaymentStatusHandler())
			.setNext(new StatusConsistencyHandler());

		return head;
	}
}
