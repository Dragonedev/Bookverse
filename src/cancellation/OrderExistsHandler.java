package cancellation;

import repository.OrderRepository;

public class OrderExistsHandler extends OrderCancellationHandler {

	private final OrderRepository repository;

	public OrderExistsHandler(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	protected void validate(CancellationContext context) {

		String orderCode = context.getOrderCode();

		if (orderCode == null || orderCode.isBlank()) {
			throw new IllegalArgumentException("Order code cannot be empty.");
		}

		context.setOrder(
				repository.findByCode(orderCode).orElseThrow(() -> new IllegalArgumentException("Order not found.")));
	}
}