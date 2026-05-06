# Product Service

Proxy microservice to [FakeStore API](https://fakestoreapi.com). Exposes product data needed by the shopping cart.

- **Port**: 8080
- **Dependencies**: None (external API only)

## API Documentation

Swagger UI: http://localhost:8080/swagger-ui/index.html

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/products` | List all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/category/{category}` | List products by category |

## Configuration

```properties
server.port=8080
fakestore.base-url=https://fakestoreapi.com
```

| Env Variable | Default | Description |
|---|---|---|
| `SERVER_PORT` | `8080` | Service port |
| `FAKESTORE_BASE_URL` | `https://fakestoreapi.com` | FakeStore API base URL |

## Running

```bash
mvn spring-boot:run
```

## Example Requests

```bash
# List all products
curl http://localhost:8080/api/products

# Get product by ID
curl http://localhost:8080/api/products/1

# List by category
curl http://localhost:8080/api/products/category/electronics
```

## Error Responses

| Code | Error Code | Description |
|------|-----------|-------------|
| 404 | `PRODUCT_NOT_FOUND` | Product does not exist |
| 400 | `INVALID_DATA` | Invalid product data from upstream |
| 500 | `INTERNAL_ERROR` | Unexpected error |
