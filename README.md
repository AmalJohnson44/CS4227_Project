
# Smart Healthcare Appointment & Monitoring System (Gradle + Next.js)

Microservices (Spring Boot + Gradle) and a role-based Next.js frontend, wired through API Gateway & Eureka.

## Services & Ports
- discovery-server (8761)
- api-gateway (8080)
- auth-service (8091)
- user-service (8092)
- patient-service (8093)
- doctor-service (8094)
- appointment-service (8095)
- medical-record-service (8096)
- admin-service (8097)
- notification-service (8098)
- frontend (3000)

## Run (no Docker)
Open terminals per service and run `gradlew.bat` in each backend folder, plus `npm run dev` in `frontend`.

## Run (Docker)
```bash
docker compose up --build
```
Open http://localhost:3000
