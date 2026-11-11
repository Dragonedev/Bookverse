
package application;

import java.util.Map;

import entities.Customer;
import entities.Order;
import entities.OrderItem;
import entities.Product;

import composite.IndividualProduct;
import composite.ProductBundle;
import composite.ProductComponent;

import controller.CheckoutController;
import controller.CancellationController;

import facade.CheckoutFacade;

import exceptions.EmptyOrderException;
import exceptions.InsufficientStockException;
import exceptions.InvalidDataException;

import cancellation.CancellationChainFactory;
import cancellation.OrderCancellationHandler;
import cancellation.OrderCancellationService;

import payment.adapter.DollarPaymentAdapter;
import payment.adapter.DollarPaymentProvider;
import payment.adapter.PaymentGateway;

import payment.factory.PaymentFactory;
import payment.factory.PixPaymentFactory;
import payment.factory.CardPaymentFactory;
import payment.factory.InternationalPaymentFactory;

import payment.product.Payment;

import payment.strategy.PaymentStrategy;
import payment.strategy.PixPaymentStrategy;
import payment.strategy.CardPaymentStrategy;
import payment.strategy.InternationalPaymentStrategy;

import payment.strategy.RefundStrategy;
import payment.strategy.PixRefundStrategy;
import payment.strategy.CardRefundStrategy;
import payment.strategy.InternationalRefundStrategy;

import repository.OrderRepository;
import repository.PaymentRepository;

import service.OrderService;
import service.PaymentService;
import service.InventoryService;

public class BookverseMain {

	public static void main(String[] args) {

		// ==========================================
		// 1. PRODUTOS DA LIVRARIA
		// ==========================================

		Product javaBook = new Product.Builder().productCode("P001").name("Java Completo").price(80.0).quantity(20)
				.build();

		Product pythonBook = new Product.Builder().productCode("P002").name("Python para Iniciantes").price(70.0)
				.quantity(15).build();

		Product cloudBook = new Product.Builder().productCode("P003").name("Cloud Computing").price(25.0).quantity(8)
				.build();

		// ==========================================
		// 2. REPOSITORIES
		// ==========================================

		OrderRepository orderRepository = new OrderRepository();

		PaymentRepository paymentRepository = new PaymentRepository();

		// ==========================================
		// 3. ADAPTER INTERNACIONAL
		// ==========================================

		PaymentGateway dollarGateway = new DollarPaymentAdapter(new DollarPaymentProvider());

		// ==========================================
		// 4. STRATEGIES DE PAGAMENTO
		// ==========================================

		Map<String, PaymentStrategy> paymentStrategies = Map.of("PIX", new PixPaymentStrategy(), "CREDIT_CARD",
				new CardPaymentStrategy(), "INTERNATIONAL", new InternationalPaymentStrategy(dollarGateway));

		// ==========================================
		// 5. STRATEGIES DE ESTORNO
		// ==========================================

		Map<String, RefundStrategy> refundStrategies = Map.of("PIX", new PixRefundStrategy(), "CREDIT_CARD",
				new CardRefundStrategy(), "INTERNATIONAL", new InternationalRefundStrategy(dollarGateway));

		// ==========================================
		// 6. SERVICES
		// ==========================================

		OrderService orderService = new OrderService(orderRepository);

		PaymentService paymentService = new PaymentService(paymentRepository, paymentStrategies, refundStrategies);

		InventoryService inventoryService = new InventoryService();

		// ==========================================
		// 7. FACADE E CONTROLLER
		// ==========================================

		CheckoutFacade checkoutFacade = new CheckoutFacade(orderService, paymentService, inventoryService);

		CheckoutController checkoutController = new CheckoutController(checkoutFacade);

		// ==========================================
		// 8. CANCELAMENTO
		// ==========================================

		OrderCancellationHandler cancellationChain = CancellationChainFactory.createDefaultChain(orderRepository,
				paymentRepository);

		OrderCancellationService cancellationService = new OrderCancellationService(cancellationChain, orderService,
				paymentService, inventoryService);

		CancellationController cancellationController = new CancellationController(cancellationService);

		// ==========================================
		// EXEMPLO 1: COMPRA COM PIX
		// ==========================================

		System.out.println("\n========== COMPRA 1: PIX ==========");

		ProductComponent javaComponent = new IndividualProduct(javaBook);

		OrderItem javaItem = new OrderItem.Builder().product(javaComponent).quantity(2).build();

		Order pixOrder = new Order.Builder().orderCode("ORD001").addItem(javaItem).build();

		Payment pixPayment = checkoutController.checkout(pixOrder, new PixPaymentFactory(), "PAY001");

		System.out.println("Pedido: " + pixOrder.getOrderCode());
		System.out.println("Pagamento: " + pixPayment.getPaymentCode());
		System.out.println("Método: " + pixPayment.getPaymentMethod());
		System.out.println("Valor: R$ " + pixPayment.getAmount());
		System.out.println("Status pagamento: " + pixPayment.getStatus());
		System.out.println("Status pedido: " + pixOrder.getStatus());
		System.out.println("Estoque Java: " + javaBook.getQuantity());

		// ==========================================
		// EXEMPLO 2: COMPRA DE KIT COM CARTÃO
		// ==========================================

		System.out.println("\n========== COMPRA 2: KIT COM CARTÃO ==========");

		ProductBundle programmingKit = new ProductBundle("Kit Programação");

		programmingKit.add(new IndividualProduct(javaBook));
		programmingKit.add(pythonBook);

		OrderItem kitItem = new OrderItem.Builder().product(programmingKit).quantity(1).build();

		Order cardOrder = new Order.Builder().orderCode("ORD002").addItem(kitItem).build();

		Payment cardPayment = checkoutController.checkout(cardOrder, new CardPaymentFactory(), "PAY002");

		System.out.println("Pedido: " + cardOrder.getOrderCode());
		System.out.println("Pagamento: " + cardPayment.getPaymentCode());
		System.out.println("Método: " + cardPayment.getPaymentMethod());
		System.out.println("Valor: R$ " + cardPayment.getAmount());
		System.out.println("Status pagamento: " + cardPayment.getStatus());
		System.out.println("Status pedido: " + cardOrder.getStatus());
		System.out.println("Estoque Java: " + javaBook.getQuantity());
		System.out.println("Estoque Python: " + pythonBook.getQuantity());

		// ==========================================
		// EXEMPLO 3: PAGAMENTO INTERNACIONAL
		// ==========================================

		System.out.println("\n========== COMPRA 3: INTERNATIONAL ==========");

		ProductComponent cloudComponent = new IndividualProduct(cloudBook);

		Order internationalOrder = new Order.Builder().orderCode("ORD003").addItem(cloudComponent, 2).build();

		Payment internationalPayment = checkoutController.checkout(internationalOrder,
				new InternationalPaymentFactory(), "PAY003");

		System.out.println("Pedido: " + internationalOrder.getOrderCode());
		System.out.println("Pagamento: " + internationalPayment.getPaymentCode());
		System.out.println("Método: " + internationalPayment.getPaymentMethod());
		System.out.println("Valor: $" + internationalPayment.getAmount());
		System.out.println("Status pagamento: " + internationalPayment.getStatus());
		System.out.println("Status pedido: " + internationalOrder.getStatus());

		// ==========================================
		// EXEMPLO 4: ESTORNO DA COMPRA COM CARTÃO
		// ==========================================

		System.out.println("\n========== ESTORNO DA COMPRA 2 ==========");

		Payment refundedPayment = cancellationController.cancelOrder("ORD002", "PAY002");

		System.out.println("Pagamento: " + refundedPayment.getPaymentCode());
		System.out.println("Status pagamento: " + refundedPayment.getStatus());

		Order cancelledOrder = orderService.findByCode("ORD002");

		System.out.println("Status pedido: " + cancelledOrder.getStatus());

		System.out.println("Estoque Java restaurado: " + javaBook.getQuantity());
		System.out.println("Estoque Python restaurado: " + pythonBook.getQuantity());

		// ==========================================
		// EXEMPLO 5: A CORRENTE BARRANDO CANCELAMENTOS INVÁLIDOS
		// ==========================================

		System.out.println("\n========== CANCELAMENTOS INVÁLIDOS ==========");

		try {
			cancellationController.cancelOrder("ORD002", "PAY002");
		} catch (IllegalStateException e) {
			System.out.println("Cancelar ORD002 de novo -> " + e.getMessage());
		}

		try {
			cancellationController.cancelOrder("ORD001", "PAY003");
		} catch (IllegalStateException e) {
			System.out.println("ORD001 com pagamento PAY003 -> " + e.getMessage());
		}

		try {
			cancellationController.cancelOrder("ORD999", "PAY001");
		} catch (IllegalArgumentException e) {
			System.out.println("Pedido inexistente -> " + e.getMessage());
		}

		// ==========================================
		// EXEMPLO 6: VALIDAÇÕES (BUILDER, ESTOQUE E STATE)
		// ==========================================

		System.out.println("\n========== VALIDAÇÕES ==========");

		try {
			new Product.Builder().productCode("P004").name("Livro sem preço").price(0.0).quantity(5).build();
		} catch (InvalidDataException e) {
			System.out.println("Builder de Product -> " + e.getMessage());
		}

		try {
			new Customer.Builder().customerCode("C001").name("Cliente").cpf("123").email("cliente@email.com").build();
		} catch (InvalidDataException e) {
			System.out.println("Builder de Customer -> " + e.getMessage());
		}

		try {
			new Order.Builder().orderCode("ORD004").build();
		} catch (EmptyOrderException e) {
			System.out.println("Builder de Order -> " + e.getMessage());
		}

		try {
			Order bigOrder = new Order.Builder().orderCode("ORD005").addItem(cloudComponent, 50).build();
			checkoutController.checkout(bigOrder, new PixPaymentFactory(), "PAY005");
		} catch (InsufficientStockException e) {
			System.out.println("Estoque -> " + e.getMessage());
		}

		try {
			refundedPayment.markAsCompleted();
		} catch (IllegalStateException e) {
			System.out.println("State (REFUNDED -> COMPLETED) -> " + e.getMessage());
		}

		// ==========================================
		// RESUMO
		// ==========================================

		System.out.println("\n========== RESUMO FINAL ==========");

		System.out.println("Java: " + javaBook.getQuantity());
		System.out.println("Python: " + pythonBook.getQuantity());
		System.out.println("Cloud: " + cloudBook.getQuantity());

		System.out.println("\nBookVerse finalizado!");
	}
}
