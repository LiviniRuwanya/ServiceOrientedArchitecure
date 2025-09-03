package com.globalbooks.shipping.service;

import com.globalbooks.shipping.dto.ShipmentRequest;
import com.globalbooks.shipping.dto.ShipmentResponse;
import com.globalbooks.shipping.model.Shipment;
import com.globalbooks.shipping.model.ShipmentStatus;
import com.globalbooks.shipping.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipmentService {
    
    @Autowired
    private ShipmentRepository shipmentRepository;
    
    public ShipmentResponse createShipment(ShipmentRequest request) {
        // Create shipment record
        Shipment shipment = new Shipment();
        shipment.setOrderId(request.getOrderId());
        shipment.setCustomerId(request.getCustomerId());
        shipment.setShippingAddress(request.getShippingAddress());
        shipment.setShippingMethod(request.getShippingMethod());
        shipment.setShippingCost(request.getShippingCost());
        
        // Generate tracking number
        String trackingNumber = "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        shipment.setTrackingNumber(trackingNumber);
        
        // Set estimated delivery (3-7 days)
        int deliveryDays = 3 + (int)(Math.random() * 5);
        LocalDateTime estimatedDelivery = LocalDateTime.now().plusDays(deliveryDays);
        shipment.setEstimatedDelivery(estimatedDelivery);
        
        // Set initial status
        shipment.setStatus(ShipmentStatus.PROCESSING);
        
        // Save shipment
        Shipment savedShipment = shipmentRepository.save(shipment);
        
        // Convert to response
        return convertToResponse(savedShipment);
    }
    
    public ShipmentResponse getShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
        return convertToResponse(shipment);
    }
    
    public ShipmentResponse getShipmentByTracking(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
        return convertToResponse(shipment);
    }
    
    public List<ShipmentResponse> getShipmentsByOrder(String orderId) {
        List<Shipment> shipments = shipmentRepository.findByOrderId(orderId);
        return shipments.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<ShipmentResponse> getShipmentsByCustomer(String customerId) {
        List<Shipment> shipments = shipmentRepository.findByCustomerId(customerId);
        return shipments.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public ShipmentResponse updateShipmentStatus(Long id, ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
        
        shipment.setStatus(status);
        
        // Update delivery time if delivered
        if (status == ShipmentStatus.DELIVERED) {
            shipment.setActualDelivery(LocalDateTime.now());
        }
        
        Shipment updatedShipment = shipmentRepository.save(shipment);
        return convertToResponse(updatedShipment);
    }
    
    private ShipmentResponse convertToResponse(Shipment shipment) {
        ShipmentResponse response = new ShipmentResponse();
        response.setId(shipment.getId());
        response.setOrderId(shipment.getOrderId());
        response.setCustomerId(shipment.getCustomerId());
        response.setShippingAddress(shipment.getShippingAddress());
        response.setShippingMethod(shipment.getShippingMethod());
        response.setShippingCost(shipment.getShippingCost());
        response.setStatus(shipment.getStatus());
        response.setTrackingNumber(shipment.getTrackingNumber());
        response.setEstimatedDelivery(shipment.getEstimatedDelivery());
        response.setActualDelivery(shipment.getActualDelivery());
        response.setCreatedAt(shipment.getCreatedAt());
        return response;
    }
}

