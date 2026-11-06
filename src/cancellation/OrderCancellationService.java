package cancellation;

import entities.Order;
import entities.enums.OrderStatus;
import payment.product.Payment;
import repository.OrderRepository;
import repository.PaymentRepository;
import service.InventoryService;
import service.OrderService;
import service.PaymentService;

public class OrderCancellationService {

	private final OrderService orderService;

	private final PaymentService paymentService;

	private final InventoryService inventoryService;

	private final OrderCancellationHandler handlerChain;

	public OrderCancellationService(OrderRepository orderRepository, PaymentRepository paymentRepository,
			OrderService orderService, PaymentService paymentService, InventoryService inventoryService) {

		this.orderService = orderService;
		this.paymentService = paymentService;
		this.inventoryService = inventoryService;

		OrderCancellationHandler orderExists = new OrderExistsHandler(orderRepository);

		OrderCancellationHandler paymentExists = new PaymentExistsHandler(paymentRepository);

		OrderCancellationHandler paymentOwnership = new PaymentOwnershipHandler();

		OrderCancellationHandler orderStatus = new OrderStatusHandler();

		OrderCancellationHandler paymentStatus = new PaymentStatusHandler();

		orderExists.setNext(paymentExists).setNext(paymentOwnership).setNext(orderStatus).setNext(paymentStatus);

		this.handlerChain = orderExists;
	}

	public Payment cancelOrder(String orderCode, String paymentCode) {

		CancellationContext context = new CancellationContext(orderCode, paymentCode);

		handlerChain.handle(context);

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