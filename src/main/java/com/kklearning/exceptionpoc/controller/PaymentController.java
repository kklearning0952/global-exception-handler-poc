package com.kklearning.exceptionpoc.controller;

import com.kklearning.exceptionpoc.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{paymentId}")
    public String getPayment(@PathVariable String paymentId) {
        return paymentService.getPayment(paymentId);
    }

    @PostMapping("/{paymentId}")
    public ResponseEntity<Map<String, String>> createPayment(
            @PathVariable String paymentId) {

        String message = paymentService.createPayment(paymentId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", message));
    }

    @GetMapping("/test-error")
    public String testError() {
        return paymentService.triggerUnexpectedError();
    }
}