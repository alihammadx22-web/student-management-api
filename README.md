# Student Management API

A REST API built with Spring Boot, Spring Security, JWT, Spring Data JPA, and PostgreSQL.

## Features

- Stateless JWT authentication
- Role-based authorization (`USER` and `ADMIN`)
- BCrypt password hashing
- Student CRUD and mark-based filtering
- Jakarta Bean Validation
- RFC 9457-style API error responses
- Environment-based configuration
- PostgreSQL Docker Compose setup
- H2-backed automated tests

## Requirements

- Java 17+
- Docker, or a local PostgreSQL instance

## Run locally

Start PostgreSQL:

```bash
docker compose up -d
```

Start the API:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs at `http://localhost:8084`.

## Authentication

Register:

```bash
curl -X POST http://localhost:8084/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"hammad","password":"strongpass123"}'
```

Login:

```bash
curl -X POST http://localhost:8084/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"hammad","password":"strongpass123"}'
```

Use the returned token:

```bash
curl http://localhost:8084/students \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## Authorization

| Method | Endpoint | Access |
|---|---|---|
| POST | `/auth/register` | Public |
| POST | `/auth/login` | Public |
| GET | `/students/**` | USER, ADMIN |
| POST | `/students` | ADMIN |
| PUT | `/students/{rollNo}` | ADMIN |
| DELETE | `/students/{rollNo}` | ADMIN |

Public registration always creates a `USER`. To create the first admin, set:

```text
ADMIN_ENABLED=true
ADMIN_USERNAME=admin
ADMIN_PASSWORD=your-strong-password
```

Restart once, then disable `ADMIN_ENABLED`.

## Configuration

Copy `.env.example` values into your environment. Never commit real database passwords or JWT secrets.

## Tests

```bash
./mvnw test
```
