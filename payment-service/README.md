# Payment Service

Simulates payment processing. Validates that the order exists and is in a payable state before processing, then notifies the order-service to update the order status.

## Architecture

Hexagonal (ports & adapters) with CQRS separation:

```
domain/          — Entities, value objects, domain exceptions (no framework dependencies)
aplicacion/
  command/       — Process and revert payment use cases, input/output ports
  query/         — Get payment use cases, input/output ports
framework/
  config/        — Spring config, bean wiring
  exceptions/    — Global exception handler
  input/         — REST controller and request/response DTOs
  output/        — JPA entity, repository, persistence adapter, HTTP client to order-service
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/payments` | Process a payment for an order |
| POST | `/api/payments/{id}/revert` | Revert a payment (refund) |
| GET | `/api/payments/{id}` | Get payment by ID |
| GET | `/api/payments/order/{orderId}` | Get payments for an order |

### Process Payment

```http
POST /api/payments
Content-Type: application/json

{
  "orderId": 1,
  "customerId": 1,
  "amount": 59.98,
  "paymentMethod": "CREDIT_CARD"
}
```

```json
HTTP 201 Created
{
  "id": 1,
  "orderId": 1,
  "status": "APPROVED",
  ...
}
```

## Setup

```bash
mvn spring-boot:run
```

The H2 console is available at `http://localhost:8082/h2-console` (JDBC URL: `jdbc:h2:mem:paymentdb`).

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | `8082` | Service port |
| `ORDER_SERVICE_URL` | `http://localhost:8083/api/orders` | Order-service base URL |

## Dependencies

Requires **order-service** to be running — validates order state before payment and notifies it after.
