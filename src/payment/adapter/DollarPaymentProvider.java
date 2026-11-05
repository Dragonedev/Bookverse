package payment.adapter;

public class DollarPaymentProvider {

	public boolean makeDollarPayment(long amountInCents, String transactionId) {

		System.out.println("Processing dollar payment...");

		return true;
	}

}