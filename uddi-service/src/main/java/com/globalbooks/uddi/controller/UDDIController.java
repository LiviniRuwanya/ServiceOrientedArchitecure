package com.globalbooks.uddi.controller;

import com.globalbooks.uddi.model.ServiceRegistry;
import com.globalbooks.uddi.service.UDDIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uddi")
@CrossOrigin(origins = "*")
public class UDDIController {
    
    @Autowired
    private UDDIService uddiService;
    
    // Register a new service
    @PostMapping("/register")
    public ResponseEntity<ServiceRegistry> registerService(@RequestBody ServiceRegistry service) {
        ServiceRegistry registered = uddiService.registerService(service);
        return ResponseEntity.ok(registered);
    }
    
    // Get all services
    @GetMapping("/services")
    public ResponseEntity<List<ServiceRegistry>> getAllServices() {
        List<ServiceRegistry> services = uddiService.getAllServices();
        return ResponseEntity.ok(services);
    }
    
    // Get service by name
    @GetMapping("/services/{serviceName}")
    public ResponseEntity<ServiceRegistry> getServiceByName(@PathVariable String serviceName) {
        ServiceRegistry service = uddiService.getServiceByName(serviceName);
        if (service != null) {
            return ResponseEntity.ok(service);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Update service status
    @PutMapping("/services/{serviceName}/status")
    public ResponseEntity<ServiceRegistry> updateServiceStatus(
            @PathVariable String serviceName, 
            @RequestParam String status) {
        ServiceRegistry updated = uddiService.updateServiceStatus(serviceName, status);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Heartbeat update
    @PutMapping("/services/{serviceName}/heartbeat")
    public ResponseEntity<ServiceRegistry> updateHeartbeat(@PathVariable String serviceName) {
        ServiceRegistry updated = uddiService.updateHeartbeat(serviceName);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Search services by type
    @GetMapping("/search")
    public ResponseEntity<List<ServiceRegistry>> searchServices(
            @RequestParam(required = false) String serviceType,
            @RequestParam(required = false) String status) {
        List<ServiceRegistry> services = uddiService.searchServices(serviceType, status);
        return ResponseEntity.ok(services);
    }
    
    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("UDDI Service Registry is running");
    }
}
