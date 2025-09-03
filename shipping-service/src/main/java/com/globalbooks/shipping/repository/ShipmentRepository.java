package com.globalbooks.shipping.repository;

import com.globalbooks.shipping.model.Shipment;
import com.globalbooks.shipping.model.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    
    List<Shipment> findByOrderId(String orderId);
    
    List<Shipment> findByCustomerId(String customerId);
    
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
    
    List<Shipment> findByStatus(ShipmentStatus status);
}

