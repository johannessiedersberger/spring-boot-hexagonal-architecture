# spring-boot-hexagonal-architecture

The hexagonal architecture uses ports and adapters that connect the core business logic of an application to the outside world.
    
- In the inside you find the core business logic (domain)
- The ports are interfaces that define how the application can interact with outside tools
- The adapters translate between the outside and the ports used by the application
- In the outside are external systems like REST-Clients, Databases or Message Brokers


https://www.happycoders.eu/wp-content/uploads/2023/06/hexagonal-architecture-java.v4-800x601.png


1. Adapter

   An adapter connects the outside world to the applications core logic (e.g. HTTP requests, database access)

   The adapter converts into formats that can be processed by the domain (JSON → Domain Objects)

   Hide the external technology details from the core business logic

   There are two types of adapters, Driving Adapters (Inbound) and Driven Adapters (Outbound)

   The Driving Adapter (Inbound) could be a REST-Controller that receives an API-Call and then calls the port (use case interface)

   The Driven Adapters (Outbound) are used by the application to interact with external systems (e.g. Databases or message brokers)

2. Port

   A Port is an interface that defines how the core application interacts with the outside. The Ports are the edges of the hexagon and only a definition of functionality without implementation

   Is defined inside the core (application/domain layer)

   A Port is independent of any framework

   Focused on what the system should do, not how


3. Example flow for creation of an order via REST

   Controller (Inbound adapter) receives POST-Request

   Controller calls the CreateOrderUseCase (Inbound Port)

   The CreateOrderService (Domain Service) is the implementation of the CreateOrderUseCase-Interface (Port)

        Validates Input for Business rules 

        Creates the Order

        saves it via OrderRepository (Outbound Port)

   The PostgresOrderRepository is the implementation of the OrderRepository 

# uml