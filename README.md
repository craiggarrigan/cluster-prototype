# cluster-prototype
Prototype for clustered web application which uses long-polling

# Technology
- nginx load balancer
  - Balances requests between multiple instances of the application
  - Sample configuration balances requests to localhost:8001 between application instances running on localhost:8080 and localhost:8081
- Hazelcast
  - Distributed topic used as event bus between application instances in the cluster
- Spring Boot
  - Long polling using DeferredResult
