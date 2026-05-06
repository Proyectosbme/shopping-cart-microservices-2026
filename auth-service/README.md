# Auth Service

Handles user registration and login. Issues JWT tokens that are shared and validated by other services (e.g. payment-service).

## API Documentation

Swagger UI: http://localhost:8081/swagger-ui/index.html

## Architecture

Hexagonal (ports & adapters) with CQRS separation:

```
domain/          — Entities, value objects, domain exceptions (no framework dependencies)
application/
  command/       — Register and login use cases, input/output ports
  query/         — Find user use case, input/output ports
framework/
  config/        — Spring Security, JWT service, password encoder, bean wiring
  exceptions/    — Global exception handler
  input/         — REST controller and request/response DTOs
  output/        — JPA entity, repository, persistence adapter, mapper
```

## Endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | `/api/auth/register` | Public | Register a new user, returns JWT |
| POST | `/api/auth/login` | Public | Authenticate user, returns JWT |

### Register

```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "secret123"
}
```

```json
HTTP 201 Created
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "secret123"
}
```

```json
HTTP 200 OK
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## Setup

1. Copy the example env file and fill in your values:

```bash
cp .env.example .env
```

2. Run the service:

```bash
./mvnw spring-boot:run
```

The H2 console is available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:authdb`).

## Environment Variables

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `JWT_SECRET` | Yes | — | Signing secret, min 32 chars. Must match across services |
| `JWT_EXPIRATION` | No | `86400000` | Token TTL in milliseconds (default 24h) |

## JWT Shared Secret

The `JWT_SECRET` must be identical in every service that validates tokens (e.g. payment-service). Tokens are signed with HS256.
