package cancellation;

public abstract class OrderCancellationHandler {

	private OrderCancellationHandler nextHandler;

	public OrderCancellationHandler setNext(OrderCancellationHandler nextHandler) {

		this.nextHandler = nextHandler;

		return nextHandler;
	}

	public final void handle(CancellationContext context) {

		validate(context);

		if (nextHandler != null) {
			nextHandler.handle(context);
		}
	}

	protected abstract void validate(CancellationContext context);

}
