package com.globalbooks.uddi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_registry")
public class ServiceRegistry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String serviceName;
    
    @NotBlank
    private String serviceType; // SOAP, REST, BPEL
    
    @NotBlank
    private String endpointUrl;
    
    @NotBlank
    private String wsdlUrl; // For SOAP services
    
    @NotNull
    private Integer port;
    
    @NotBlank
    private String status; // ACTIVE, INACTIVE, DEPRECATED
    
    @NotNull
    private LocalDateTime lastHeartbeat;
    
    @NotNull
    private LocalDateTime registeredAt;
    
    private String version;
    private String description;
    private String sla; // Service Level Agreement
    
    // Constructors
    public ServiceRegistry() {}
    
    public ServiceRegistry(String serviceName, String serviceType, String endpointUrl, 
                         String wsdlUrl, Integer port, String status) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.endpointUrl = endpointUrl;
        this.wsdlUrl = wsdlUrl;
        this.port = port;
        this.status = status;
        this.lastHeartbeat = LocalDateTime.now();
        this.registeredAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
    public String getEndpointUrl() { return endpointUrl; }
    public void setEndpointUrl(String endpointUrl) { this.endpointUrl = endpointUrl; }
    
    public String getWsdlUrl() { return wsdlUrl; }
    public void setWsdlUrl(String wsdlUrl) { this.wsdlUrl = wsdlUrl; }
    
    public Integer getPort() { return port; }
    public void setPort(Integer port) { this.port = port; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getLastHeartbeat() { return lastHeartbeat; }
    public void setLastHeartbeat(LocalDateTime lastHeartbeat) { this.lastHeartbeat = lastHeartbeat; }
    
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getSla() { return sla; }
    public void setSla(String sla) { this.sla = sla; }
}
