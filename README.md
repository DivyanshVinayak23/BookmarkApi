# Bookmark API - Dockerized

This project contains a Spring Boot application for managing bookmarks, with Kafka integration for asynchronous processing.

## Prerequisites

- Docker
- Docker Compose

## Getting Started

### Setup and Run

1. Clone the repository
2. Make sure you have Docker and Docker Compose installed
3. Run the application stack:

```bash
docker-compose up -d
```

This will start:
- MongoDB database
- Zookeeper
- Kafka broker
- Bookmark API application
- Python Kafka producer (in standby mode)

### Services

- **Bookmark API**: http://localhost:8080
- **MongoDB**: localhost:27017
- **Kafka**: localhost:9092

### Using the API

The API provides endpoints for managing bookmarks:

- `GET /bookmarks` - List all bookmarks
- `GET /bookmarks/{id}` - Get a specific bookmark
- `POST /bookmarks` - Create a new bookmark
- `PUT /bookmarks/{id}` - Update a bookmark
- `DELETE /bookmarks/{id}` - Delete a bookmark

### Using the Kafka Producer

The Python Kafka producer is included but not automatically started. To use it:

1. Access the container:
```bash
docker-compose exec kafka-producer bash
```

2. Run the producer:
```bash
python kafka_producer.py
```

Alternatively, you can modify the producer code and restart just that service:
```bash
docker-compose up -d --build kafka-producer
```

## Stopping the Application

```bash
docker-compose down
```

To remove volumes as well (this will delete all data):
```bash
docker-compose down -v
```

## Project Structure

- `bookmark-api/` - Spring Boot application
- `kafka-producer/` - Python Kafka producer
- `docker-compose.yml` - Docker Compose configuration
- `README.md` - This documentation

## Notes for Development

- The MongoDB data is persisted in a Docker volume `mongo-data`
- Kafka is configured with a single broker for development purposes
- For production, consider using a more robust Kafka setup with multiple brokers
