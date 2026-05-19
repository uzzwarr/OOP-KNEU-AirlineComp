# ✈️ AviaCompany — Airline Management System

> Full-stack airline management application built as a coursework project for **Object-Oriented Programming** at **Kyiv National Economic University (KNEU)**.
> A complete journey from Java fundamentals and OOP design patterns through database persistence to a modern REST API with a Vue.js single-page front-end.

[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue.js-3.4-42b883?logo=vuedotjs)](https://vuejs.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8-blue?logo=mysql)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Project Evolution](#-project-evolution)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Repository Structure](#-repository-structure)
- [Getting Started](#-getting-started)
- [REST API Reference](#-rest-api-reference)
- [Front-End Features](#-front-end-features)
- [Testing](#-testing)
- [Screenshots](#-screenshots)
- [Design Patterns Implemented](#-design-patterns-implemented)
- [Database Schema](#-database-schema)
- [Author](#-author)
- [License](#-license)

---

## 🎯 Overview

**AviaCompany** is an educational information system for managing airline operations — flights, crew assignments, and administrative access. The project began as a series of console-based OOP exercises and gradually evolved into a fully-fledged client-server web application with a modern reactive front-end.

The final stage (Individual Coursework / ІДЗ) delivers:

- 🛡️ A secure **REST API** built on Spring Boot 3.2 with JWT-based authentication
- 🗃️ Persistent storage in **MySQL** via Spring Data JPA / Hibernate
- 🎨 A responsive **Vue 3 SPA** with Vuex state management, Vue Router 4 navigation, and Bootstrap 5 styling
- 📬 A ready-to-import **Postman collection** for API testing
- 🔁 Complete **CRUD** workflows for two core entities (`Flight` and `CrewMember`)

---

## 📚 Project Evolution

The repository preserves the full learning path across multiple lab assignments:

| Lab | Topic | Key Concepts |
|-----|-------|--------------|
| **Lab 2** | Core OOP | Classes, encapsulation, inheritance, polymorphism (`Airline`, `Flight`, `Crew`, `Administrator`, `Dispatcher`) |
| **Lab 3** | Structural Design Patterns | Facade, Decorator, Flyweight |
| **Lab 4** | Behavioral Design Patterns | Strategy, Observer, State (pricing strategies, flight observers, baggage calculator) |
| **Lab 5** | JPA & Hibernate Basics | `Flight`, `Passenger`, `Passport`, `Airplane` entities; DAO pattern |
| **Lab 6** | Generic DAO & Validation | `GenericDao<T>`, Bean Validation, `Ticket` entity |
| **Lab 7** | Spring Boot MVC | Server-side rendering with FreeMarker, session-based auth |
| **Lab 8** *(IDZ)* | **REST + SPA** | REST controllers, JWT, CORS, Vue 3 client |

> ℹ️ Each lab is self-contained in its own package. The Individual Coursework (`Lab8` + `com.kneu`) is the production-grade culmination of the series.

---

## 🛠️ Tech Stack

### Back-End

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Language |
| Spring Boot | 3.2.5 | Application framework |
| Spring Web | — | REST controllers |
| Spring Data JPA | — | ORM & repositories |
| Hibernate | 6.x | JPA implementation |
| MySQL Connector/J | 8.x | Database driver |
| jjwt | 0.12.5 | JWT signing & parsing |
| Jakarta Validation | — | Bean validation |
| Maven | 3.8+ | Build tool |

### Front-End

| Technology | Version | Purpose |
|------------|---------|---------|
| Vue.js | 3.4 | Reactive framework |
| Vue Router | 4.3 | Client-side routing |
| Vuex | 4.1 | State management |
| Axios | 1.7 | HTTP client |
| Bootstrap | 5.3 | UI framework |
| Bootstrap Icons | 1.11 | Icon library |
| Vite | 5.2 | Build tool & dev server |

### Database

- **MySQL 8** (schema auto-created on first launch)

---

## 🏗️ Architecture

```
┌───────────────────────┐         HTTP/JSON          ┌────────────────────────┐
│                       │  ────────────────────────► │                        │
│   Vue 3 SPA           │       JWT in header        │   Spring Boot API      │
│   (localhost:5173)    │                            │   (localhost:8080)     │
│                       │  ◄──────────────────────── │                        │
│   • Vue Router        │                            │   • REST Controllers   │
│   • Vuex (auth/ui)    │                            │   • JWT Filter         │
│   • Axios interceptor │                            │   • Services           │
│   • Bootstrap 5       │                            │   • JPA Repositories   │
└───────────────────────┘                            └───────────┬────────────┘
                                                                 │ JDBC
                                                                 ▼
                                                     ┌────────────────────────┐
                                                     │   MySQL 8              │
                                                     │   avia_company_db      │
                                                     │   • flights            │
                                                     │   • crew_members       │
                                                     │   • flight_crew        │
                                                     │   • admin_users        │
                                                     └────────────────────────┘
```

**Request flow (protected endpoint):**

1. User submits credentials → `POST /api/auth/login`
2. Server validates against `admin_users` table → issues signed JWT
3. Front-end stores token in `localStorage` + Vuex
4. Axios interceptor adds `Authorization: Bearer <token>` to every outgoing request
5. `JwtAuthFilter` validates the token; on success request reaches the controller
6. On `401`, the Axios response interceptor clears auth state and redirects to `/login`

---

## 📁 Repository Structure

```
OOP-KNEU-AirlineComp/
│
├── pom.xml                              ← Maven build descriptor
│
├── src/
│   ├── Lab2/                            ← Lab 2: Core OOP
│   ├── Lab3/                            ← Lab 3: Structural patterns
│   ├── Lab4/                            ← Lab 4: Behavioral patterns
│   │
│   ├── main/
│   │   ├── java/
│   │   │   ├── Lab5/                    ← Lab 5: JPA / Hibernate
│   │   │   ├── Lab6/                    ← Lab 6: Generic DAO
│   │   │   │
│   │   │   ├── Lab8/                    ★ FINAL COURSEWORK (REST API)
│   │   │   │   ├── entity/              JPA entities
│   │   │   │   │   ├── Flight.java
│   │   │   │   │   ├── CrewMember.java
│   │   │   │   │   └── AdminUser.java
│   │   │   │   ├── repository/          Spring Data JPA repositories
│   │   │   │   ├── service/             Business logic
│   │   │   │   ├── controller/          REST controllers
│   │   │   │   │   ├── FlightRestController.java
│   │   │   │   │   ├── CrewMemberRestController.java
│   │   │   │   │   └── AuthRestController.java
│   │   │   │   ├── dto/                 Data Transfer Objects
│   │   │   │   ├── security/            JWT utilities & filter
│   │   │   │   ├── config/              CORS & filter registration
│   │   │   │   └── Lab8DataInitializer.java
│   │   │   │
│   │   │   └── com/kneu/
│   │   │       └── AviaCompanyIdzApplication.java   ← Main Spring Boot entry
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/java/                       ← Lab 5 & Lab 6 unit tests
│
└── frontend/                            ← Vue 3 SPA (separate project)
    ├── package.json
    ├── vite.config.js
    ├── index.html
    └── src/
        ├── main.js                      Entry point
        ├── App.vue                      Root component (navbar, footer)
        ├── router/index.js              Vue Router + auth guards
        ├── store/index.js               Vuex (auth + ui modules)
        ├── services/                    Axios HTTP wrappers
        │   ├── api.js                   Configured Axios + JWT interceptor
        │   ├── authService.js
        │   ├── flightService.js
        │   └── crewService.js
        ├── views/                       Page components
        │   ├── HomeView.vue
        │   ├── LoginView.vue
        │   ├── FlightsView.vue
        │   ├── FlightFormView.vue
        │   ├── CrewView.vue
        │   ├── CrewFormView.vue
        │   └── AboutView.vue
        └── assets/main.css              Custom theme
```

---

## 🚀 Getting Started

### Prerequisites

- **JDK 17** or higher
- **Maven 3.8+**
- **Node.js 18+** with npm
- **MySQL 8** running locally on port `3306`

### 1. Clone the repository

```bash
git clone https://github.com/uzzwarr/OOP-KNEU-AirlineComp.git
cd OOP-KNEU-AirlineComp
```

### 2. Configure the database

The application uses these credentials by default (edit `src/main/resources/application.properties` if yours differ):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/avia_company_db?serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

> 💡 The schema and tables are created automatically on first launch (`ddl-auto=update` + `createDatabaseIfNotExist=true`).

### 3. Start the back-end

```bash
mvn spring-boot:run
```

The API server starts on **http://localhost:8080**.

On first run the seed data initializer creates:
- Admin user: **`admin` / `1234`**
- 4 crew members (pilot, co-pilot, steward, flight attendant)
- 3 sample flights with assigned crews

Look for this confirmation in the logs:

```
[Lab8] Created admin: admin / 1234
[Lab8] Created crew members: 4
[Lab8] Created sample flights: 3
```

### 4. Start the front-end

In a new terminal:

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server opens **http://localhost:5173** in your browser.

### 5. Log in & explore

- Click **Login** in the navbar
- Use credentials `admin` / `1234`
- Navigate to **Flights** or **Crew** and try the full CRUD flow

---

## 📡 REST API Reference

Base URL: `http://localhost:8080/api`

### Authentication

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `POST` | `/auth/login` | — | Authenticate and receive a JWT |
| `GET` | `/auth/me` | JWT | Verify token and return current user |

**Sample login request:**

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "1234"
}
```

**Sample response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "type": "Bearer"
}
```

### Flights

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `GET` | `/flights` | — | List all flights |
| `GET` | `/flights/{id}` | — | Get one flight |
| `POST` | `/flights` | JWT | Create a new flight |
| `PUT` | `/flights/{id}` | JWT | Update an existing flight |
| `DELETE` | `/flights/{id}` | JWT | Delete a flight |

**Sample flight payload:**

```json
{
  "flightNumber": "KNEU-FK67",
  "departureCity": "Kyiv",
  "arrivalCity": "Lviv",
  "departureTime": "2026-06-15T10:00:00",
  "arrivalTime": "2026-06-15T12:00:00",
  "crewIds": [1, 2, 3]
}
```

### Crew Members

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| `GET` | `/crew` | — | List all crew members |
| `GET` | `/crew/{id}` | — | Get one crew member |
| `POST` | `/crew` | JWT | Create a new crew member |
| `PUT` | `/crew/{id}` | JWT | Update an existing crew member |
| `DELETE` | `/crew/{id}` | JWT | Delete a crew member |

**Sample crew member payload:**

```json
{
  "fullName": "Ivan Ivanenko",
  "role": "Pilot",
  "experienceYears": 12,
  "licenseNumber": "UA-001-PL"
}
```

**Available roles:** `Pilot`, `CoPilot`, `Steward`, `FlightAttendant`

### Error responses

| Status | Meaning |
|--------|---------|
| `400` | Validation error (missing or invalid fields) |
| `401` | Missing or invalid JWT |
| `404` | Resource not found |
| `409` | Conflict (e.g. duplicate flight number) |

---

## 🎨 Front-End Features

### Pages

| Route | Page | Description |
|-------|------|-------------|
| `/` | Home | Welcome screen with system statistics |
| `/login` | Login | Admin authentication form |
| `/flights` | Flights list | Searchable table, delete confirmation modal |
| `/flights/new` | Create flight | Form with crew multi-select (auth required) |
| `/flights/:id/edit` | Edit flight | Pre-filled form (auth required) |
| `/crew` | Crew list | Card grid with role filter |
| `/crew/new` | Create crew | Member form (auth required) |
| `/crew/:id/edit` | Edit crew | Pre-filled form (auth required) |
| `/about` | About | System info, endpoints, tech stack |

### Highlights

- ⚡ **Lazy-loaded routes** — each view loads on demand for faster initial render
- 🛡️ **Auth guards** — protected routes redirect unauthenticated users to login
- 💾 **Persistent session** — JWT survives page reloads via `localStorage`
- 🔄 **Auto-logout on 401** — Axios interceptor handles expired tokens gracefully
- 🔔 **Toast notifications** — non-blocking feedback for every action
- ❓ **Confirmation modals** — prevent accidental deletes
- 🔍 **Live search & filters** — instant client-side filtering on lists
- 📱 **Responsive design** — works on mobile, tablet, and desktop

---

## 🧪 Testing

### Postman collection

Import `AviaCompany-IDZ-Postman-collection.json` into Postman. The collection is organised into four folders:

1. **Auth** — login & token validation (login auto-saves token to a collection variable)
2. **Flights CRUD** — all five operations
3. **Crew CRUD** — all five operations
4. **Negative tests** — `401`, `404`, `400` scenarios for the report

### Workflow

1. Run `POST /auth/login` — the token is stored automatically via test script
2. Run any subsequent request — the saved token is injected by the request header `Authorization: Bearer {{token}}`
3. No copy-paste required between requests

### Manual testing checklist

- [ ] Login with correct credentials → receive token
- [ ] Login with wrong credentials → `401`
- [ ] `GET /api/flights` without auth → works (public read)
- [ ] `POST /api/flights` without auth → `401`
- [ ] `POST /api/flights` with auth → flight created
- [ ] `PUT /api/flights/{id}` → flight updated
- [ ] `DELETE /api/flights/{id}` → flight removed
- [ ] Same flow for `/api/crew`
- [ ] Validation: send empty payload → `400` with field errors

---

## 🖼️ Screenshots

> *Add screenshots to a `docs/screenshots/` folder and reference them here for your final report.*

| Page | File |
|------|------|
| Home dashboard | `docs/screenshots/home.png` |
| Login form | `docs/screenshots/login.png` |
| Flights list | `docs/screenshots/flights.png` |
| Flight form | `docs/screenshots/flight-form.png` |
| Crew grid | `docs/screenshots/crew.png` |
| Postman: login | `docs/screenshots/postman-login.png` |
| Postman: CRUD | `docs/screenshots/postman-crud.png` |
| MySQL Workbench | `docs/screenshots/db.png` |

---

## 🧩 Design Patterns Implemented

Throughout Lab 3 and Lab 4 the following GoF patterns were applied to the airline domain:

| Pattern | Type | Use Case |
|---------|------|----------|
| **Strategy** | Behavioral | Interchangeable pricing algorithms (`StandardPricingStrategy`, `LowCostPricingStrategy`) |
| **Observer** | Behavioral | Notifying admins & dispatchers about flight changes |
| **State** | Behavioral | Flight lifecycle (scheduled, boarding, in-flight, landed) |
| **Facade** | Structural | Simplified airline service entry point |
| **Decorator** | Structural | Adding VIP services to flights at runtime |
| **Flyweight** | Structural | Sharing geometric shape data efficiently |
| **Builder** | Creational | Fluent construction of `Flight` objects |
| **DAO** | Architectural | Data-access abstraction (Lab 5, Lab 6) |

---

## 🗄️ Database Schema

```
admin_users          flights                       crew_members
┌──────────┐         ┌─────────────────────┐       ┌─────────────────────┐
│ id (PK)  │         │ id (PK)             │       │ id (PK)             │
│ username │         │ flight_number       │       │ full_name           │
│ password │         │ departure_city      │       │ role                │
└──────────┘         │ arrival_city        │       │ experience_years    │
                     │ departure_time      │       │ license_number      │
                     │ arrival_time        │       └─────────────────────┘
                     └─────────────────────┘                  ▲
                              ▲                               │
                              │  flight_crew (junction)       │
                              │  ┌────────────────────────┐   │
                              └──┤ flight_id (FK)         ├───┘
                                 │ crew_id   (FK)         │
                                 └────────────────────────┘
```

A flight can have many crew members and a crew member can be assigned to many flights — modelled as a **many-to-many** relationship through the `flight_crew` junction table.

---

## 🗺️ Roadmap / Possible Improvements

- [ ] Replace plain-text passwords with **BCrypt hashing**
- [ ] Migrate to **Spring Security** for fine-grained role-based access
- [ ] Add **refresh tokens** alongside short-lived access tokens
- [ ] Introduce additional entities: `Passenger`, `Ticket`, `Airplane`
- [ ] Add server-side pagination & sorting
- [ ] Write integration tests with **MockMvc** and **Testcontainers**
- [ ] Containerise the stack with **Docker Compose**
- [ ] Add **OpenAPI / Swagger UI** documentation
- [ ] Migrate Vue front-end to **TypeScript** and the Composition API
- [ ] Deploy via **GitHub Actions** to a free-tier cloud provider

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

Originally developed for educational purposes as part of the Object-Oriented Programming course at KNEU. You are free to use, modify, and distribute the code for any purpose, including commercial use, provided that the original copyright notice and this permission notice are included.


