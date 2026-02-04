# DirtyHands Backend Learning Plan (SDE2 Simulation)

This README is a step-by-step, end-to-end backend development path that simulates real SDE2 responsibilities: feature delivery, code quality, reliability, security, and monitoring. Each phase includes concrete deliverables and a definition of done.

## Run Locally
Prerequisites:
- Java 17

Commands:
1. Start the app:
   ```bash
   ./mvnw spring-boot:run
   ```
2. Verify the API:
   ```bash
   curl -s http://localhost:8080/api/users
   ```

## What You Will Build
You will evolve this project into a production-grade service with:
- Clean API design and validation
- Database migrations and transactional correctness
- Testing at multiple levels
- Observability (logs, metrics, tracing)
- Secure access control
- CI/CD-ready packaging and deployability
- Operational readiness (SLOs, alerts, incident response)

## Phase 0: Baseline Setup
Goal: Make the project stable and repeatable for local dev.

Tasks:
1. Confirm app boots with H2 and a basic endpoint.
2. Add `README` run instructions and a `.env.example` if needed.
3. Ensure `application.yaml` has clear dev config.

Definition of Done:
1. `./mvnw spring-boot:run` starts the app.
2. `GET /api/users` returns a response.

## Phase 1: API Design and DTOs
Goal: Separate API payloads from persistence models.

Tasks:
1. Use request/response DTOs for all endpoints.
2. Add Bean Validation with `@Valid` and `@NotBlank`.
3. Add global error handling with `@RestControllerAdvice`.

Definition of Done:
1. Invalid requests return `400` with field-level errors.
2. No endpoint accepts an entity directly as a request body.

## Phase 2: Persistence and Migrations
Goal: Replace H2 with a real database and manage schema changes.

Tasks:
1. Add Postgres (Docker) and configure local connection.
2. Add Flyway or Liquibase.
3. Move `ddl-auto` to `none` for non-dev profiles.

Definition of Done:
1. Schema is created by migrations only.
2. App boots using Postgres with persisted data across restarts.

## Phase 3: Business Logic and Transactions
Goal: Ensure correctness across multi-step operations.

Tasks:
1. Add service-layer methods with `@Transactional`.
2. Implement update, delete, and fetch-by-id flows.
3. Define domain rules and enforce them in services.

Definition of Done:
1. Multi-step operations are transactional.
2. Consistent error handling for not found and invalid state.

## Phase 4: Testing Strategy
Goal: Add reliable tests like a real production team expects.

Tasks:
1. Unit tests for service logic.
2. `@DataJpaTest` for repository behavior.
3. `@WebMvcTest` for controller validation behavior.
4. Testcontainers-based integration tests for DB.

Definition of Done:
1. Core flows are covered by tests.
2. CI can run tests in under a few minutes.

## Phase 5: Security Basics
Goal: Add authentication and authorization.

Tasks:
1. Add Spring Security.
2. Protect write endpoints with auth.
3. Add a simple role-based policy.

Definition of Done:
1. Unauthenticated requests to protected endpoints fail.
2. Authorized requests succeed.

## Phase 6: Observability and Monitoring
Goal: See what your service is doing in production.

Tasks:
1. Add Spring Boot Actuator.
2. Add Micrometer metrics.
3. Add structured logging and trace IDs.
4. Add OpenTelemetry tracing.
5. Create dashboards (Grafana) and alerts (Prometheus).

Definition of Done:
1. You can answer: latency, error rate, and throughput.
2. There is at least one alert for error-rate spikes.

## Phase 7: Performance and Resilience
Goal: Be safe under load and failure.

Tasks:
1. Add pagination for list endpoints.
2. Add rate limiting.
3. Add timeouts and retries where needed.
4. Load test with k6 or JMeter.

Definition of Done:
1. The service handles a defined RPS without errors.
2. Backpressure is applied when overloaded.

## Phase 8: Deployment and CI/CD
Goal: Ship safely and repeatably.

Tasks:
1. Add Dockerfile.
2. Add GitHub Actions workflow for tests and build.
3. Add environment-specific configs and secrets handling.

Definition of Done:
1. A build artifact is produced in CI.
2. The image can run in a clean environment.

## Phase 9: Reliability and On-Call Simulation
Goal: Simulate real SDE2 operational responsibilities.

Tasks:
1. Define SLOs (e.g., 99.9% success rate).
2. Create an incident drill: break something on purpose.
3. Write a postmortem with root cause and action items.

Definition of Done:
1. You can detect, triage, and fix an incident.
2. You can write a clear postmortem with preventive work.

## Real-World Ticket Simulation
You will treat each phase as real tickets:
1. Write a short design note for each feature.
2. Create tasks and acceptance criteria.
3. Implement, test, and document.
4. Do a self-review before “merging.”

## Why SDE2 Can Feel “Dangerous”
SDE2 is a high-impact role. It can feel dangerous because:
1. Your changes can affect production reliability and user data.
2. Mistakes can cause outages, security incidents, or cost spikes.
3. You’re expected to own systems end-to-end, not just code.
4. You might be on-call and responsible for incident response.

This “danger” is manageable with good engineering discipline:
1. Strong test coverage and validation.
2. Observability so issues surface quickly.
3. Safe rollouts and clear rollback paths.
4. Postmortems to prevent repeat failures.

## Suggested Order of Execution
1. Phase 1: API design and validation
2. Phase 2: Persistence and migrations
3. Phase 3: Business logic
4. Phase 4: Testing
5. Phase 5: Security
6. Phase 6: Observability
7. Phase 7: Performance
8. Phase 8: Deployment
9. Phase 9: Reliability

If you want, I can turn Phase 1 into actionable tasks and implement the first set now.
