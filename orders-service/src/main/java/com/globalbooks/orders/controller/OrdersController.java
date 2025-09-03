package com.globalbooks.orders.controller;

import com.globalbooks.orders.model.OrderRequest;
import com.globalbooks.orders.model.OrderResponse;
import com.globalbooks.orders.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping(path = "/{id}")
    public OrderResponse getOrder(@PathVariable("id") String orderId) {
        return orderService.getOrder(orderId);
    }
}
