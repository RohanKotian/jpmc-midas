# JPMC MIDAS Project
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Event--Driven-black)
![Docker](https://img.shields.io/badge/Docker-Kafka%20via%20Docker-blue)

This repository contains my implementation of the **JPMorgan Chase & Co. Market Data Ingestion and Distribution System (MIDAS)** as part of the Forage virtual experience program.

The project demonstrates a simplified backend system exposing REST APIs and integrating with messaging infrastructure commonly used in financial systems.

---

## 📌 Overview

MIDAS (Market Data Ingestion and Distribution System) is designed to:
- Expose backend APIs for querying financial data
- Demonstrate backend system design in a financial context
- Integrate messaging infrastructure using Apache Kafka
- Emphasize clean structure and maintainability

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Apache Kafka
- Docker (Kafka provisioning)
- Maven
- REST APIs
- JUnit

---

## 🏗️ Architecture

The project follows a **component-based Spring Boot architecture**:
- REST controllers handle API requests
- Core domain models are isolated in a foundation layer
- Infrastructure concerns (e.g., Kafka, database conduits) are separated from API logic

This structure promotes clarity and separation of concerns without overengineering.

---

## 📂 Project Structure

```text
jpmc-midas
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/jpmc/midascore
│   │   │       ├── component
│   │   │       │   ├── BalanceRestController.java
│   │   │       │   └── DatabaseConduit.java
│   │   │       ├── foundation
│   │   │       │   └── Balance.java
│   │   │       └── MidasApplication.java
│   │   └── resources
│   │       └── application.yml
│   │
│   └── test
│       └── java
│           └── com/jpmc/midascore
│               └── MidasApplicationTests.java
│
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 🔌 API Endpoints
| Method | Endpoint |	Description |
|--------|----------|-------------|
| GET	| /balance |	Retrieve account balance for a given user ID |


Example Request
```bash
GET /balance?userId=123
```
---

## ▶️ How to Run the Project

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose

### Steps:
#### Clone the repository
```bash
git clone https://github.com/RohanKotian/jpmc-midas.git
cd jpmc-midas
```
#### Start Kafka via Docker
```bash
docker compose up -d
```

#### Build and run the Spring Boot app
```bash
mvn clean install
mvn spring-boot:run
```
The application will start on the default Spring Boot port.

## 🧪 Testing
Run unit tests using:

bash
```
mvn test
```
## 📚 What I Learned
- Designing RESTful backend services

- Structuring a Spring Boot application for scalability

- Applying clean code and separation of concerns

- Understanding data ingestion workflows in financial systems

## 📄 Notes
This project was completed as part of a guided virtual experience to demonstrate backend development and system design concepts commonly used in real-world financial applications.

## 👤 Author
Rohan Kotian

