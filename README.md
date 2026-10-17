# EBank-AI Enterprise Platform

> Production-grade Event-Driven Microservices Banking Platform built with Spring Boot 4, Spring Cloud Gateway & Keycloak OAuth2/OIDC. Features an autonomous AI Agent powered by Spring AI, Model Context Protocol (MCP) & RAG pgvector.

---

## Table of Contents
- [Overview](#overview)
- [System Architecture](#system-architecture)
- [Tech Stack](#tech-stack)
- [Microservices Catalog](#microservices-catalog)
- [Getting Started](#getting-started)
- [Git Flow & Branching Strategy](#git-flow--branching-strategy)
- [Project Roadmap](#project-roadmap)
- [License](#license)

---

## Overview

EBank-AI Enterprise Platform is a distributed, event-driven banking platform designed following Domain-Driven Design (DDD) principles and Zero-Trust security standards.

The system manages core banking domain operations (customer profiles, bank accounts, balances, and real-time transactions) while integrating an autonomous AI Agent capable of querying internal systems via the Model Context Protocol (MCP) and performing Retrieval-Augmented Generation (RAG) on banking documentation stored in vector databases.

---

## System Architecture

The architecture relies on a microservices pattern divided into 4 core business domain services and 2 infrastructure management services:

- Client Layer: Angular 21 Single Page Application (managed in a dedicated repository)
- Edge & Routing Layer: Spring Cloud Gateway with reactive security filter chain
- Identity Provider: Keycloak 24 (OAuth2 Resource Server / OpenID Connect)
- Core Business Services: Customer Service, Account Service, Audit Notification Service, AI Agent Service
- Infrastructure Services: Discovery Service (Netflix Eureka), PostgreSQL databases, Apache Kafka, pgvector

---

## Tech Stack

- Core Framework: Java 21, Spring Boot 4.1.1
- Service Discovery & API Gateway: Netflix Eureka Server, Spring Cloud Gateway
- Security & Identity: Keycloak 24, OAuth2, JWT, Spring Security Reactive
- Persistence & Vector Store: PostgreSQL 16, Spring Data JPA, pgvector
- Event Messaging: Apache Kafka
- Artificial Intelligence: Spring AI, Model Context Protocol (MCP), RAG
- Inter-Service Communication: OpenFeign (Synchronous), Kafka (Asynchronous)
- Build & Containerization: Apache Maven (Multi-Module), Docker, Docker Compose

---

## Microservices Catalog

| Service Name | Port | Service Type | Description |
| :--- | :--- | :--- | :--- |
| discovery-service | 8761 | Infrastructure | Service registration and discovery engine (Netflix Eureka) |
| gateway-service | 8888 | Infrastructure | Unified entry point, dynamic routing, reactive JWT validation |
| customer-service | 8081 | Core Business | Customer lifecycle, identity verification, profile management |
| account-service | 8082 | Core Business | Bank accounts, balances, transactions, OpenFeign integration |
| audit-notification-service | 8083 | Core Business | Asynchronous transaction auditing and notifications via Kafka |
| ai-agent-service | 8084 | Core Business | AI Agent using MCP tools and pgvector RAG for smart operations |

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- Apache Maven 3.9+
- Docker Engine & Docker Compose
- Git CLI

### Local Setup (Milestone 1 Baseline)

1. Clone the repository:
   ```bash
   git clone https://github.com/hichambenhsine/ebank-ai-enterprise-platform.git
   cd ebank-ai-enterprise-platform
   ```

2. Start database containers:
   ```bash
   docker-compose up -d postgres-customer postgres-account
   ```

3. Build all modules:
   ```bash
   mvn clean install -DskipTests
   ```

4. Run Customer Service:
   ```bash
   cd customer-service
   mvn spring-boot:run
   ```

5. Run Account Service:
   ```bash
   cd ../account-service
   mvn spring-boot:run
   ```

---

## Git Flow & Branching Strategy

This repository enforces Conventional Commits and a short-lived feature branching policy:

- main: Protected branch. Contains verified and deployment-ready code.
- feature/<scope>-<description>: Isolated feature branches for iterative development.

Commit Naming Convention:
`<type>(<scope>): <short descriptive imperative summary>`

Examples:
- `feat(customer): implement domain entity, repository, and rest endpoints`
- `feat(account): add openfeign customer client integration`
- `chore(config): initialize parent pom.xml and repository baseline`

---

## Project Roadmap

- [ ] v0.1.0-M1 (Milestone 1): Core Microservices & Inter-Service Communication (OpenFeign)
- [ ] v0.2.0-M2 (Milestone 2): Service Discovery & Dynamic Routing (Eureka & Gateway)
- [ ] v0.3.0-M3 (Milestone 3): Zero-Trust Security Implementation (Keycloak & OAuth2/JWT)
- [ ] v0.4.0-M4 (Milestone 4): Event-Driven Architecture & Audit (Apache Kafka)
- [ ] v0.5.0-M5 (Milestone 5): Autonomous AI Agent & MCP Protocol (Spring AI & pgvector)
- [ ] v1.0.0 (Release): Production Deployment, Observability & Docker Compose Orchestration

---

## License

Distributed under the MIT License.
