# GlobalBooks Orders Service (REST)

A Spring Boot REST microservice providing order creation and lookup.

## 🚀 Run Locally
```bash
cd orders-service
mvn spring-boot:run
```
- Base URL: `http://localhost:8080`

## 🔗 Endpoints
- POST `/orders` — Create order
- GET `/orders/{id}` — Get order status

## 📦 Example Requests

### Create Order (POST /orders)
```json
{
  "bookId": "123",
  "quantity": 2,
  "customer": "Lily"
}
```
Response:
```json
{
  "orderId": "1",
  "status": "CONFIRMED"
}
```

### Get Order (GET /orders/1)
Response:
```json
{
  "orderId": "1",
  "status": "CONFIRMED"
}
```

## 🧾 JSON Schema
File: `src/main/resources/schemas/order-create.schema.json`

## 🧪 Postman
- Import collection: `postman/OrdersService.postman_collection.json`
- Run "Create Order" then "Get Order"

## 📎 Notes
- Uses in-memory storage; data resets on restart.
- Port 8080 to match assignment instructions.
