package com.globalbooks.uddi.repository;

import com.globalbooks.uddi.model.ServiceRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRegistryRepository extends JpaRepository<ServiceRegistry, Long> {
    
    Optional<ServiceRegistry> findByServiceName(String serviceName);
    
    List<ServiceRegistry> findByServiceType(String serviceType);
    
    List<ServiceRegistry> findByStatus(String status);
    
    @Query("SELECT s FROM ServiceRegistry s WHERE " +
           "(:serviceType IS NULL OR s.serviceType = :serviceType) AND " +
           "(:status IS NULL OR s.status = :status)")
    List<ServiceRegistry> searchServices(@Param("serviceType") String serviceType, 
                                       @Param("status") String status);
    
    @Query("SELECT s FROM ServiceRegistry s WHERE s.lastHeartbeat < :threshold")
    List<ServiceRegistry> findInactiveServices(@Param("threshold") java.time.LocalDateTime threshold);
}
