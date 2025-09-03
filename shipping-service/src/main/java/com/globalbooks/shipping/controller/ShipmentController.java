package com.globalbooks.shipping.controller;

import com.globalbooks.shipping.dto.ShipmentRequest;
import com.globalbooks.shipping.dto.ShipmentResponse;
import com.globalbooks.shipping.model.ShipmentStatus;
import com.globalbooks.shipping.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shipping")
@CrossOrigin(origins = "*")
public class ShipmentController {
    
    @Autowired
    private ShipmentService shipmentService;
    
    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@Valid @RequestBody ShipmentRequest request) {
        ShipmentResponse response = shipmentService.createShipment(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable Long id) {
        ShipmentResponse response = shipmentService.getShipment(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ShipmentResponse> getShipmentByTracking(@PathVariable String trackingNumber) {
        ShipmentResponse response = shipmentService.getShipmentByTracking(trackingNumber);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ShipmentResponse>> getShipmentsByOrder(@PathVariable String orderId) {
        List<ShipmentResponse> responses = shipmentService.getShipmentsByOrder(orderId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ShipmentResponse>> getShipmentsByCustomer(@PathVariable String customerId) {
        List<ShipmentResponse> responses = shipmentService.getShipmentsByCustomer(customerId);
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<ShipmentResponse> updateStatus(
            @PathVariable Long id, 
            @RequestParam ShipmentStatus status) {
        ShipmentResponse response = shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Shipping Service is running!");
    }
}

