# Shopping Cart Microservices

A shopping cart system built with Spring Boot following hexagonal architecture across four independent microservices.

## Services

| Service | Port | Description |
|---------|------|-------------|
| [auth-service](./auth-service) | 8081 | User registration and login — issues JWT tokens |
| [product-service](./product-service) | 8080 | Proxy to FakeStore API — product catalog |
| [payment-service](./payment-service) | 8082 | Payment simulation with order validation |
| [order-service](./order-service) | 8083 | Order lifecycle management |

## API Documentation

Each service exposes interactive API docs via Swagger UI once running:

| Service | Swagger UI |
|---------|-----------|
| product-service | http://localhost:8080/swagger-ui/index.html |
| auth-service | http://localhost:8081/swagger-ui/index.html |
| payment-service | http://localhost:8082/swagger-ui/index.html |
| order-service | http://localhost:8083/swagger-ui/index.html |

## Architecture

Each service follows **Hexagonal Architecture (Ports & Adapters)** with CQRS separation:

```
domain/         ← Entities, value objects, domain exceptions (no framework dependencies)
application/
  command/      ← Write use cases, input/output ports
  query/        ← Read use cases, input/output ports
framework/
  config/       ← Spring config, bean wiring
  exceptions/   ← Global exception handler
  input/        ← REST controllers, request/response DTOs
  output/       ← JPA adapters, HTTP clients, mappers
```

## Service Communication

```
auth-service (8081)
        │ issues JWT
        ↓
  ┌─────────────────────────────────────┐
  │  JWT validated by each service      │
  └─────────────────────────────────────┘

product-service (8080)
        ↑
        │ validates products on order creation
order-service (8083) ←──── payment-service (8082)
        │                         │
        └── marks order PAID ─────┘
        └── reverts to PENDING on refund
```

## Running Locally

Start services in this order:

```bash
# Terminal 1 — no dependencies
cd product-service && mvn spring-boot:run

# Terminal 2 — no dependencies
cd auth-service && mvn spring-boot:run

# Terminal 3 — depends on product-service
cd order-service && mvn spring-boot:run

# Terminal 4 — depends on order-service
cd payment-service && mvn spring-boot:run
```

> Each service requires its own `.env` file. Copy `.env.example` and fill in the values.  
> The `JWT_SECRET` must be identical across all services that validate tokens.

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Security + JJWT (auth-service)
- Spring Data JPA + H2
- OpenFeign (product-service)
- RestTemplate (payment-service)
- Lombok
