package com.kklearning.exceptionpoc.service;

import com.kklearning.exceptionpoc.exception.DuplicatePaymentException;
import com.kklearning.exceptionpoc.exception.PaymentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {

    private final Map<String, String> payments = Map.of(
            "1001", "SUCCESS",
            "1002", "PENDING"
    );

    public String getPayment(String paymentId) {
        String status = payments.get(paymentId);

        if (status == null) {
            throw new PaymentNotFoundException(
                    "Payment not found: " + paymentId
            );
        }

        return status;
    }

    public String createPayment(String paymentId) {
        if (payments.containsKey(paymentId)) {
            throw new DuplicatePaymentException(
                    "Payment already exists: " + paymentId
            );
        }

        return "Payment created: " + paymentId;
    }

    public String triggerUnexpectedError() {
        throw new IllegalStateException(
                "Simulated unexpected error"
        );
    }
}