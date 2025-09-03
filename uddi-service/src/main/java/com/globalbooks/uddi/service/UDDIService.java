package com.globalbooks.uddi.service;

import com.globalbooks.uddi.model.ServiceRegistry;
import com.globalbooks.uddi.repository.ServiceRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UDDIService {
    
    @Autowired
    private ServiceRegistryRepository repository;
    
    public ServiceRegistry registerService(ServiceRegistry service) {
        // Set default values
        if (service.getRegisteredAt() == null) {
            service.setRegisteredAt(LocalDateTime.now());
        }
        if (service.getLastHeartbeat() == null) {
            service.setLastHeartbeat(LocalDateTime.now());
        }
        if (service.getStatus() == null) {
            service.setStatus("ACTIVE");
        }
        
        return repository.save(service);
    }
    
    public List<ServiceRegistry> getAllServices() {
        return repository.findAll();
    }
    
    public ServiceRegistry getServiceByName(String serviceName) {
        Optional<ServiceRegistry> service = repository.findByServiceName(serviceName);
        return service.orElse(null);
    }
    
    public ServiceRegistry updateServiceStatus(String serviceName, String status) {
        Optional<ServiceRegistry> serviceOpt = repository.findByServiceName(serviceName);
        if (serviceOpt.isPresent()) {
            ServiceRegistry service = serviceOpt.get();
            service.setStatus(status);
            return repository.save(service);
        }
        return null;
    }
    
    public ServiceRegistry updateHeartbeat(String serviceName) {
        Optional<ServiceRegistry> serviceOpt = repository.findByServiceName(serviceName);
        if (serviceOpt.isPresent()) {
            ServiceRegistry service = serviceOpt.get();
            service.setLastHeartbeat(LocalDateTime.now());
            return repository.save(service);
        }
        return null;
    }
    
    public List<ServiceRegistry> searchServices(String serviceType, String status) {
        return repository.searchServices(serviceType, status);
    }
    
    public List<ServiceRegistry> getServicesByType(String serviceType) {
        return repository.findByServiceType(serviceType);
    }
    
    public List<ServiceRegistry> getActiveServices() {
        return repository.findByStatus("ACTIVE");
    }
    
    public List<ServiceRegistry> getInactiveServices() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5); // 5 minutes threshold
        return repository.findInactiveServices(threshold);
    }
}
