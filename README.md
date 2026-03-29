# 🚀 PulseDesk Backend

AI-powered ticket management system built with **Spring Boot + Hugging Face AI + REST APIs**



# Overview

PulseDesk is a backend application that collects user comments from different channels and automatically determines whether a comment should become a support ticket.

Using **Hugging Face Inference API**, the system analyzes text and generates structured ticket data including:

* Title
* Category (bug / feature / billing / account / other)
* Priority (low / medium / high)
* Summary

The application also includes a simple frontend UI and is deployed publicly.



# Features

* Submit and store user comments
* AI-based classification using Hugging Face
* Automatic ticket generation
* RESTful API endpoints
* H2 / JPA database storage
* Interactive frontend dashboard
* Ticket filtering (High / Medium / Low)
* Chart visualization (Chart.js)
* Deployed using Docker + Render
* Basic authentication for restricted access


# Tech Stack

* **Backend:** Spring Boot (Java 17)
* **Database:** H2 / JPA / Hibernate
* **AI Integration:** Hugging Face Inference API
* **Frontend:** HTML, CSS, JavaScript, Chart.js
* **Deployment:** Docker + Render
* **Security:** Spring Security (Basic Auth / Form Login)



# API Endpoints

## Comments

* `POST /api/comments`
  Submit a comment

* `GET /api/comments`
  Retrieve all comments

## Tickets

* `GET /api/tickets`
  Retrieve all generated tickets

* `GET /api/tickets/{id}`
  Retrieve a specific ticket


# AI Processing

The system uses Hugging Face models to:

1. Analyze comment text
2. Determine if it represents an issue
3. Generate structured ticket data

Example:

Input:

> "I was charged twice for my subscription"

Output:

* Category: billing
* Priority: high
* Title: Double charge issue
* Summary: User charged twice


# 🌐 Live Demo (Deployed)

👉 https://pulsdesk-backend-3.onrender.com


## ⚙️ Setup Instructions

## 1. Clone Repository

```
git clone https://github.com/itu-shadow/pulsdesk-backend.git
cd pulsdesk-backend
```


## 2. Run Application

Using Maven:

```
./mvnw spring-boot:run
```

or on Windows:

```
mvnw.cmd spring-boot:run
```


### 3. Access App

```
http://localhost:8080
```


## 🐳 Docker Deployment

Build image:

```
docker build -t pulsdesk .
```

Run container:

```
docker run -p 8080:8080 pulsdesk
```

---

##  UI Features

* Submit comments via form
* Select channel (web / email / slack)
* View tickets dynamically
* Filter by priority
* Visual analytics using charts



## 📁 Project Structure

```
com.adonis.pulsedesk
├── controller
├── service
├── repository
├── entity
├── config
└── resources
    └── static (frontend)
```


## 🧾 Notes

* AI responses may vary depending on model output
* Designed for demonstration and learning purposes
* Uses in-memory authentication for simplicity

---

## 🏁 Conclusion

This project demonstrates:

* Full-stack development
* AI integration into backend systems
* REST API design
* Deployment using modern tools

---

## 👨‍💻 Author

Yash Kumar

---

## ⭐ Bonus Achievements

Custom UI
AI-powered logic
Dockerized deployment
Public hosting

---
