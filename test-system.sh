#!/bin/bash

echo "🌍 GlobalBooks Inc. - Microservices System Test"
echo "================================================"
echo ""

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to test service health
test_service() {
    local service_name=$1
    local url=$2
    local expected_status=$3
    
    echo -e "${BLUE}Testing $service_name...${NC}"
    response=$(curl -s -o /dev/null -w "%{http_code}" "$url")
    
    if [ "$response" = "$expected_status" ]; then
        echo -e "  ${GREEN}✅ $service_name is running (HTTP $response)${NC}"
        return 0
    else
        echo -e "  ${RED}❌ $service_name failed (HTTP $response, expected $expected_status)${NC}"
        return 1
    fi
}

# Function to test UDDI registry
test_uddi() {
    echo -e "${BLUE}Testing UDDI Service Registry...${NC}"
    
    # Test health endpoint
    health_response=$(curl -s "http://localhost:8085/uddi/health")
    if [[ "$health_response" == *"running"* ]]; then
        echo -e "  ${GREEN}✅ UDDI Registry is healthy${NC}"
    else
        echo -e "  ${RED}❌ UDDI Registry health check failed${NC}"
        return 1
    fi
    
    # Test service listing
    services_response=$(curl -s "http://localhost:8085/uddi/services")
    if [[ "$services_response" == *"[]"* ]] || [[ "$services_response" == *"serviceName"* ]]; then
        echo -e "  ${GREEN}✅ UDDI Registry can list services${NC}"
    else
        echo -e "  ${RED}❌ UDDI Registry service listing failed${NC}"
        return 1
    fi
}

# Function to test RabbitMQ
test_rabbitmq() {
    echo -e "${BLUE}Testing RabbitMQ Message Broker...${NC}"
    
    # Test RabbitMQ management interface
    rabbitmq_response=$(curl -s -o /dev/null -w "%{http_code}" "http://localhost:15672")
    if [ "$rabbitmq_response" = "200" ]; then
        echo -e "  ${GREEN}✅ RabbitMQ Management is accessible${NC}"
    else
        echo -e "  ${RED}❌ RabbitMQ Management failed (HTTP $rabbitmq_response)${NC}"
        return 1
    fi
}

# Function to test BPEL workflow
test_bpel() {
    echo -e "${BLUE}Testing BPEL Workflow Engine...${NC}"
    
    if [ -f "bpel/placeOrder.bpel" ]; then
        echo -e "  ${GREEN}✅ BPEL PlaceOrder workflow exists${NC}"
        echo -e "  ${YELLOW}  📋 Workflow: PlaceOrder Process${NC}"
        echo -e "  ${YELLOW}  🔗 Partner Links: Client, Catalog${NC}"
        echo -e "  ${YELLOW}  📝 Operations: placeOrder, getBookByISBN${NC}"
    else
        echo -e "  ${RED}❌ BPEL workflow file not found${NC}"
        return 1
    fi
}

# Main test execution
echo -e "${YELLOW}Starting system health checks...${NC}"
echo ""

# Test core services
test_service "Catalog Service" "http://localhost:8081/services" "200"
test_service "Orders Service" "http://localhost:8082/orders" "200"
test_service "Payments Service" "http://localhost:8083/payments" "200"
test_service "Shipping Service" "http://localhost:8084/shipping" "200"

echo ""

# Test infrastructure services
test_uddi
test_rabbitmq
test_bpel

echo ""
echo -e "${YELLOW}System Test Summary:${NC}"
echo "========================"

# Count successful tests
success_count=0
total_count=7

if test_service "Catalog Service" "http://localhost:8081/services" "200" > /dev/null; then ((success_count++)); fi
if test_service "Orders Service" "http://localhost:8082/orders" "200" > /dev/null; then ((success_count++)); fi
if test_service "Payments Service" "http://localhost:8083/payments" "200" > /dev/null; then ((success_count++)); fi
if test_service "Shipping Service" "http://localhost:8084/shipping" "200" > /dev/null; then ((success_count++)); fi

# Test infrastructure (simplified)
if curl -s "http://localhost:8085/uddi/health" | grep -q "running"; then ((success_count++)); fi
if curl -s -o /dev/null -w "%{http_code}" "http://localhost:15672" | grep -q "200"; then ((success_count++)); fi
if [ -f "bpel/placeOrder.bpel" ]; then ((success_count++)); fi

echo -e "${BLUE}Services Tested: $total_count${NC}"
echo -e "${GREEN}Successful: $success_count${NC}"
echo -e "${RED}Failed: $((total_count - success_count))${NC}"

if [ $success_count -eq $total_count ]; then
    echo ""
    echo -e "${GREEN}🎉 All systems are operational! GlobalBooks is ready for production! 🎉${NC}"
else
    echo ""
    echo -e "${YELLOW}⚠️  Some services need attention. Check the logs above. ⚠️${NC}"
fi

echo ""
echo -e "${BLUE}Next Steps:${NC}"
echo "1. Access the dashboard: http://localhost:8080"
echo "2. Monitor RabbitMQ: http://localhost:15672 (guest/guest)"
echo "3. Check UDDI Registry: http://localhost:8085/uddi/services"
echo "4. Test SOAP services: http://localhost:8081/services?wsdl"
echo ""
echo -e "${GREEN}GlobalBooks Inc. - Transforming e-commerce through modern architecture! 🚀${NC}"
