# Order Service

Manages order creation, cancellation, and status transitions. Validates products against the product-service before creating an order, and exposes endpoints for the payment-service to mark orders as paid or revert them.

## Architecture

Hexagonal (ports & adapters) with CQRS separation:

```
domain/          — Entities, value objects, domain exceptions (no framework dependencies)
aplicacion/
  command/       — Create and cancel order use cases, input/output ports
  query/         — Get and list order use cases, input/output ports
framework/
  config/        — Spring config, bean wiring
  exceptions/    — Global exception handler
  input/         — REST controller and request/response DTOs
  output/        — JPA entity, repository, persistence adapter, HTTP client
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/orders` | Create a new order |
| GET | `/api/orders/{id}` | Get order by ID |
| GET | `/api/orders?customerId={id}` | List orders by customer |
| PATCH | `/api/orders/{id}/cancel` | Cancel an order |
| PATCH | `/api/orders/{id}/pay` | Mark order as paid (called by payment-service) |
| PATCH | `/api/orders/{id}/revert` | Revert order to pending (called by payment-service) |

### Create Order

```http
POST /api/orders
Content-Type: application/json

{
  "customerId": 1,
  "customerName": "John Doe",
  "customerEmail": "john@example.com",
  "details": [
    {
      "productId": 1,
      "productName": "Product Name",
      "quantity": 2,
      "unitPrice": 29.99
    }
  ]
}
```

## Setup

```bash
mvn spring-boot:run
```

The H2 console is available at `http://localhost:8083/h2-console` (JDBC URL: `jdbc:h2:mem:orderdb`).

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | `8083` | Service port |
| `ORDER_SERVICE_URL` | `http://localhost:8083/api/orders` | Self-reference used by payment-service |
| `PRODUCT_SERVICE_URL` | `http://localhost:8080/api/products` | Product-service base URL |

## Dependencies

Requires **product-service** to be running for order creation (product validation).
