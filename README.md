
# Smart Healthcare — All-in-One (Docker Compose Only)

This project contains a fully runnable Smart Healthcare microservices system with **MySQL** and a **Next.js** frontend.
**Run using Docker Compose only.**

## Quick start
```bash
# Start database
docker compose up -d mysql

# Build & run all services + frontend
docker compose up --build
```

Open:
- Frontend: http://localhost:3000
- Eureka:   http://localhost:8761

### Demo accounts
- admin1 / pass
- doctor1 / pass
- patient1 / pass
