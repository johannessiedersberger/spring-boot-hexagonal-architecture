# Spring Boot Hexagonal Architecture

A reference implementation of **Hexagonal Architecture** (also known as the **Ports and Adapters** pattern) built with Spring Boot. This architectural style isolates core business logic from external frameworks, databases, and delivery mechanisms, making the application easier to test, maintain, and evolve.

![Hexagonal Architecture Diagram](https://www.happycoders.eu/wp-content/uploads/2023/06/hexagonal-architecture-java.v4-800x601.png)
*Source: [HappyCoders.eu](https://www.happycoders.eu/)*

## Core Concepts

The architecture is structured around four distinct layers, enforcing strict dependency rules where dependencies always point inward toward the domain:

* **Domain (Core):** Contains the core business logic, entities, and domain rules. It has zero dependencies on external frameworks or infrastructure.
* **Ports:** Framework-independent interfaces defined inside the core that dictate *what* the application can do, without specifying *how*.
* **Adapters:** Components that translate between external technologies and the application's ports (e.g., mapping JSON payloads to domain objects).
* **External Systems:** Third-party infrastructure such as REST clients, relational databases, and message brokers.

---

## Architecture Components

### 1. Ports
Ports form the boundary of the hexagon. They are pure Java interfaces defined within the core application/domain layer and contain no implementation details.

* **Inbound (Driving) Ports:** Define the use cases exposed by the application (e.g., `CreateOrderUseCase`).
* **Outbound (Driven) Ports:** Define the interfaces required by the application to communicate with external dependencies (e.g., `OrderRepository`).

### 2. Adapters
Adapters sit outside the core and handle communication with the outside world, hiding technical and framework-specific details from the domain logic.

* **Driving (Inbound) Adapters:** Trigger application behavior by invoking Inbound Ports. *Example:* A Spring `@RestController` that accepts an HTTP request, maps the DTO to a domain command, and calls a use case.
* **Driven (Outbound) Adapters:** Implement Outbound Ports to interact with external infrastructure. *Example:* A persistence adapter (`PostgresOrderRepository`) that maps domain entities to JPA entities and saves them to PostgreSQL.

---

## Example Flow: Creating an Order via REST

The following sequence illustrates how a request travels through the layers when creating a new order:

1. **Receive Request:** The REST Controller *(Inbound Adapter)* receives an incoming `POST` request.
2. **Invoke Use Case:** The controller maps the request payload and calls the `CreateOrderUseCase` *(Inbound Port)*.
3. **Execute Business Logic:** `CreateOrderService` *(Domain Service)* implements the `CreateOrderUseCase` and executes the workflow:
   * Validates the input against core business rules.
   * Instantiates the `Order` domain entity.
   * Persists the order by calling the `OrderRepository` *(Outbound Port)*.
4. **Persist Data:** `PostgresOrderRepository` *(Outbound Adapter)* implements `OrderRepository` and writes the record to the database.

```text
[REST Client] 
      │ (HTTP POST)
      ▼
[OrderController]        ──► Inbound Adapter
      │
      ▼
[CreateOrderUseCase]     ──► Inbound Port (Interface)
      │
      ▼
[CreateOrderService]     ──► Domain / Application Core
      │
      ▼
[OrderRepository]        ──► Outbound Port (Interface)
      │
      ▼
[PostgresOrderRepository]──► Outbound Adapter
      │
      ▼
[(PostgreSQL)]           ──► External System
