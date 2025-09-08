#  GlobalBooks Inc. - Microservices SOA Architecture

##  Project Overview

**GlobalBooks Inc.** has transformed from a monolithic Java application to a modern, scalable **Service-Oriented Architecture (SOA)** with microservices. This project demonstrates enterprise-grade SOA implementation with dual interface support, service orchestration, and comprehensive governance.

## Architecture Overview

### **Core Services**
- **Catalog Service** (Port 8081) - SOAP Web Service with JAX-WS
- **Orders Service** (Port 8082) - Spring Boot REST API
- **Payments Service** (Port 8083) - Spring Boot REST API  
- **Shipping Service** (Port 8084) - Spring Boot REST API
- **UDDI Service Registry** (Port 8085) - Central service discovery

### **Integration & Orchestration**
- **RabbitMQ** - Enterprise Service Bus (ESB) for asynchronous messaging
- **BPEL Engine** - Business Process Execution Language for workflow orchestration
- **Docker & Docker Compose** - Containerization and orchestration

## Quick Start

### **Prerequisites**
- Docker & Docker Compose
- Java 11+ (for local development)
- Maven 3.6+

### **Start All Services**
```bash
docker-compose up -d
```

### **Access Services**
- **Catalog Service**: http://localhost:8081/services
- **Orders Service**: http://localhost:8082/orders
- **Payments Service**: http://localhost:8083/payments
- **Shipping Service**: http://localhost:8084/shipping
- **UDDI Registry**: http://localhost:8085/uddi
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)
- **System Dashboard**: Open `dashboard.html` in browser

## Project Structure

```
GlobalBooks-SOA/
├── catalog-service/           # SOAP Web Service (JAX-WS)
├── orders-service/            # Spring Boot REST API
├── payments-service/          # Spring Boot REST API
├── shipping-service/          # Spring Boot REST API
├── uddi-service/              # Service Registry & Discovery
├── bpel/                      # BPEL Process Definitions
├── test-suites/               # Testing & Validation Tools
├── doc/                       # Documentation
├── docker-compose.yml         # Service orchestration
├── dashboard.html             # System monitoring dashboard
└── test-system.sh            # Health check script
```

---

## DELIVERABLES CHECKLIST

### **1. Design Artifacts (100% Complete)**

| Deliverable | File | Description |
|-------------|------|-------------|
| **SOA Design Document** | `SOA_Design_Document.md` | Complete SOA architecture design, patterns, and implementation details |
| **WSDL Files** | `catalog-service/src/main/resources/CatalogService.wsdl` | SOAP service contract definitions |
| **UDDI Entries** | `uddi-service/` directory | Complete service registry implementation |
| **Governance Policy** | `Governance_Policy.md` | SOA governance framework and policies |

### **2. Source Code (100% Complete)**

| Deliverable | File | Description |
|-------------|------|-------------|
| **CatalogService (Java SOAP WAR)** | `catalog-service/` | JAX-WS SOAP web service with WSDL generation |
| **OrdersService (Spring Boot REST)** | `orders-service/` | RESTful microservice for order management |
| **BPEL Process Definitions** | `bpel/placeOrder.bpel` | Business process orchestration workflows |
| **Integration Code/Config** | `docker-compose.yml` | Service orchestration and RabbitMQ integration |

### **3. Configuration Files (100% Complete)**

| Deliverable | File | Description |
|-------------|------|-------------|
| **sun-jaxws.xml** | `catalog-service/src/main/webapp/WEB-INF/sun-jaxws.xml` | JAX-WS deployment configuration |
| **web.xml** | `catalog-service/src/main/webapp/WEB-INF/web.xml` | Web application configuration |
| **Spring Security/OAuth2** | Built into service implementations | Security framework integration |
| **Integration Exchanges/Queues** | `docker-compose.yml` | RabbitMQ message broker configuration |
| **BPEL Deployment Descriptors** | `bpel/` directory | Process deployment and configuration |

### **4. Test Suites (100% Complete)**

| Deliverable | File | Description |
|-------------|------|-------------|
| **SOAP UI Project** | `test-suites/GlobalBooks_SOAP_UI_Project.xml` | Complete SOAP UI test suite with test cases |
| **Postman/cURL Scripts** | `test-suites/GlobalBooks_Postman_Collection.json` | REST API testing collection for all services |
| **BPEL Engine Console Logs** | `Integration_Queue_Status.md` | Process execution logs and monitoring |
| **Integration Queue Status** | `Integration_Queue_Status.md` | RabbitMQ monitoring and performance metrics |

### **5. Reflective Report (100% Complete)**

| Deliverable | File | Description |
|-------------|------|-------------|
| **Trade-off Analysis** | `Reflective_Report_Trade_Off_Analysis.md` | Comprehensive analysis of architectural decisions |


## Technical Features

### **Service Communication**
- **SOAP** - Legacy partner integration via JAX-WS
- **REST** - Modern client integration via Spring Boot
- **Message Queues** - Asynchronous communication via RabbitMQ
- **Service Discovery** - Centralized registry via UDDI service

### **Security & Governance**
- **WS-Security** - SOAP service security tokens
- **OAuth2** - REST API authentication
- **SLA Management** - 99.5% uptime, <200ms response times
- **Versioning** - Service version control and deprecation

### **Monitoring & Operations**
- **Health Checks** - Service status monitoring
- **Performance Metrics** - Response time and throughput tracking
- **Error Handling** - Dead letter queues and retry mechanisms
- **Dashboard** - Real-time system status visualization

## Performance Targets

- **Response Time**: <200ms for 95% of requests
- **Uptime**: 99.5% availability
- **Throughput**: 1,000+ requests/minute
- **Scalability**: Horizontal scaling via Docker containers

## Running the Project

### **1. Start All Services**
```bash
# Start all microservices and infrastructure
docker-compose up -d

# Check service status
./test-system.sh
```

### **2. Test SOAP Service**
```bash
# Access WSDL
curl http://localhost:8081/services?wsdl

# Test SOAP operations using SOAP UI project
# Import: test-suites/GlobalBooks_SOAP_UI_Project.xml
```

### **3. Test REST Services**
```bash
# Import Postman collection
# File: test-suites/GlobalBooks_Postman_Collection.json

# Test UDDI registry
curl http://localhost:8085/uddi/health
```

### **4. Monitor System**
```bash
# Open dashboard
open dashboard.html

# Check RabbitMQ
open http://localhost:15672
```

## Testing & Validation

### **SOAP Testing**
- Import `test-suites/GlobalBooks_SOAP_UI_Project.xml` into SOAP UI
- Test all 3 operations: `getBook`, `listBooks`, `addBook`
- Validate SOAP responses, schema compliance, and SLA

### **REST Testing**
- Import `test-suites/GlobalBooks_Postman_Collection.json` into Postman
- Test all microservices: Orders, Payments, Shipping, UDDI
- Validate responses and error handling

### **Integration Testing**
- Monitor message flow through RabbitMQ
- Execute BPEL processes and validate workflows
- Check service discovery and health monitoring

## Documentation Files

| File | Purpose | Content |
|------|---------|---------|
| `README.md` | Project overview and quick start | This file - complete project guide |
| `SOA_Design_Document.md` | Architecture design | SOA patterns, service design, integration |
| `Governance_Policy.md` | Governance framework | Policies, procedures, compliance |
| `Reflective_Report_Trade_Off_Analysis.md` | Analysis report | Trade-offs, decisions, lessons learned |
| `Viva_Demo_Script.md` | Demonstration guide | Step-by-step viva presentation |
| `Integration_Queue_Status.md` | Integration monitoring | Queue status, BPEL logs, performance |
| `dashboard.html` | System dashboard | Real-time service status visualization |
| `test-system.sh` | Health check script | Automated service validation |

## Learning Outcomes

This project demonstrates mastery of:
- **SOA Principles** - Service orientation, loose coupling, reusability
- **Microservices Architecture** - Service decomposition and independence
- **Integration Patterns** - Message queues, service orchestration
- **Enterprise Standards** - SOAP, REST, BPEL, UDDI
- **DevOps Practices** - Docker, monitoring, testing automation
- **Governance** - SLA management, versioning, compliance


Demo link : https://drive.google.com/file/d/1KDkyW-nLacTKURfc-vPp7ytui9bqMk_t/view?usp=sharing

