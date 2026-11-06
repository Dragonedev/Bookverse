package payment.state;

public interface PaymentState {

	PaymentState complete();

	PaymentState cancel();

	PaymentState refund();

	String getStatus();

}