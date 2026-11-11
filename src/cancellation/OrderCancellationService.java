package cancellation;

import entities.Order;
import entities.enums.OrderStatus;
import payment.product.Payment;
import service.InventoryService;
import service.OrderService;
import service.PaymentService;

public class OrderCancellationService {

	private final OrderService orderService;

	private final PaymentService paymentService;

	private final InventoryService inventoryService;

	private final OrderCancellationHandler validationChain;

	public OrderCancellationService(OrderCancellationHandler validationChain, OrderService orderService,
			PaymentService paymentService, InventoryService inventoryService) {

		this.validationChain = validationChain;
		this.orderService = orderService;
		this.paymentService = paymentService;
		this.inventoryService = inventoryService;
	}

	public Payment cancelOrder(String orderCode, String paymentCode) {

		CancellationContext context = new CancellationContext(orderCode, paymentCode);

		validationChain.handle(context);

		Order order = context.getOrder();

		Payment payment = context.getPayment();

		boolean completedOrder = order.getStatus() == OrderStatus.COMPLETED;

		boolean operationCompleted = paymentService.cancelOrRefund(payment);

		if (!operationCompleted) {
			throw new IllegalStateException("Payment cancellation or refund failed.");
		}

		if (completedOrder) {
			inventoryService.restoreStock(order.getItems());
		}

		order.cancel();

		orderService.update(order);

		return payment;
	}
}
