
# Real-Time Inventory Tracker

This is a Spring Boot project demonstrating real-time inventory tracking using Kafka.

## Features
- REST API for inventory updates
- Kafka producer and consumer integration
- Real-time stock update processing
- H2 in-memory database for inventory tracking

## How to Run
1. Start a Kafka broker and Zookeeper.
2. Run the Spring Boot application (`RealTimeInventoryTrackerApplication`).
3. Use REST endpoints to interact with the system.

## REST Endpoints
- `POST /api/inventory/update` - Send stock updates.
- `GET /api/inventory` - Get current inventory.

## Configuration
- Kafka server: `localhost:9092`
- H2 Database: In-memory
