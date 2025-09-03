package com.globalbooks.orders.service;

import com.globalbooks.orders.model.OrderRequest;
import com.globalbooks.orders.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final Map<String, String> orderIdToStatus = new ConcurrentHashMap<>();
    private final AtomicLong orderIdSequence = new AtomicLong(0);

    public OrderResponse createOrder(OrderRequest orderRequest) {
        String newOrderId = String.valueOf(orderIdSequence.incrementAndGet());
        orderIdToStatus.put(newOrderId, "CONFIRMED");
        return new OrderResponse(newOrderId, "CONFIRMED");
    }

    public OrderResponse getOrder(String orderId) {
        String status = orderIdToStatus.getOrDefault(orderId, "NOT FOUND");
        return new OrderResponse(orderId, status);
    }
}
