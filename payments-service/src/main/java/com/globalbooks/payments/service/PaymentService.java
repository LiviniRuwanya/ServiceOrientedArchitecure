package com.globalbooks.payments.service;

import com.globalbooks.payments.dto.PaymentRequest;
import com.globalbooks.payments.dto.PaymentResponse;
import com.globalbooks.payments.model.Payment;
import com.globalbooks.payments.model.PaymentStatus;
import com.globalbooks.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public PaymentResponse processPayment(PaymentRequest request) {
        // Create payment record
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setCustomerId(request.getCustomerId());
        payment.setAmount(BigDecimal.valueOf(request.getAmount()));
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setCardLast4(request.getCardLast4());
        
        // Generate transaction ID
        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        payment.setTransactionId(transactionId);
        
        // Simulate payment processing
        try {
            // Simulate processing delay
            Thread.sleep(1000);
            
            // Simulate success/failure (90% success rate)
            if (Math.random() > 0.1) {
                payment.setStatus(PaymentStatus.COMPLETED);
            } else {
                payment.setStatus(PaymentStatus.FAILED);
            }
        } catch (InterruptedException e) {
            payment.setStatus(PaymentStatus.FAILED);
            Thread.currentThread().interrupt();
        }
        
        // Save payment
        Payment savedPayment = paymentRepository.save(payment);
        
        // Convert to response
        return convertToResponse(savedPayment);
    }
    
    public PaymentResponse getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToResponse(payment);
    }
    
    public PaymentResponse getPaymentByTransaction(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToResponse(payment);
    }
    
    public List<PaymentResponse> getPaymentsByOrder(String orderId) {
        List<Payment> payments = paymentRepository.findByOrderId(orderId);
        return payments.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<PaymentResponse> getPaymentsByCustomer(String customerId) {
        List<Payment> payments = paymentRepository.findByCustomerId(customerId);
        return payments.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    private PaymentResponse convertToResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setOrderId(payment.getOrderId());
        response.setCustomerId(payment.getCustomerId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());
        response.setTransactionId(payment.getTransactionId());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setCardLast4(payment.getCardLast4());
        response.setCreatedAt(payment.getCreatedAt());
        return response;
    }
}

