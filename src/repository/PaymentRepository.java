package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import payment.product.Payment;

public class PaymentRepository {

    private final Map<String, Payment> payments = new HashMap<>();

    public void save(Payment payment) {
        payments.put(payment.getPaymentCode(), payment);
    }

    public Optional<Payment> findByCode(String paymentCode) {
        return Optional.ofNullable(payments.get(paymentCode));
    }

    public void delete(String paymentCode) {
        payments.remove(paymentCode);
    }

    public boolean existsByCode(String paymentCode) {
        return payments.containsKey(paymentCode);
    }
}