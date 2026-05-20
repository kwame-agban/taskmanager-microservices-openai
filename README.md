# README.md

# Task Manager Microservices OpenAI

Application de gestion de tâches basée sur une architecture microservices Spring Boot avec intégration OpenAI.

## Architecture du projet

L'application est composée de plusieurs microservices :

```text
frontend-angular        → Frontend Angular
api-gateway             → API Gateway Spring Cloud Gateway
auth-service            → Authentification JWT
task-service            → Gestion des tâches
ai-assistant-service    → Assistant IA OpenAI
ai-common-library       → Librairie commune IA
ai-ops-service          → Service IA Ops
ai-planner-service      → Service IA Planning
ai-review-service       → Service IA Review
```

---

# Stack technique

## Backend

- Java 21
- Spring Boot 3
- Spring Cloud Gateway
- Spring Security JWT
- Maven
- OpenAI API

## Frontend

- Angular
- TypeScript
- RxJS

## Infrastructure

- Docker
- Docker Compose
- Kubernetes (k8s)

---

# Ports des services

| Service | Port |
|---|---|
| Frontend Angular | 4200 |
| API Gateway | 8080 |
| Auth Service | 8081 |
| Task Service | 8082 |
| AI Assistant Service | 8086 |

---

# Prérequis

Installer :

- Java 21
- Maven 3.9+
- Node.js 18+
- Angular CLI
- Git
- Docker Desktop (optionnel)

Vérifier les versions :

```bash
java -version
mvn -version
node -v
npm -v
ng version
```

---

# Cloner le projet

```bash
git clone https://github.com/kwame-agban/taskmanager-microservices-openai.git

cd taskmanager-microservices-openai
```

---

# Configuration OpenAI

Créer une clé API OpenAI :

https://platform.openai.com/api-keys

Ajouter du crédit API :

https://platform.openai.com/settings/organization/billing/overview

## Configuration de la variable d'environnement

Sous Git Bash :

```bash
export OPENAI_API_KEY="sk-proj-xxxxxxxxxxxxxxxx"
```

Vérifier :

```bash
echo $OPENAI_API_KEY
```

---

# Configuration API Gateway

Fichier :

```text
api-gateway/src/main/resources/application.yml
```

Configuration recommandée :

```yaml
server:
  port: ${SERVER_PORT:8080}

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      server:
        webflux:
          routes:

            - id: auth-service
              uri: ${AUTH_SERVICE_URL:http://localhost:8081}
              predicates:
                - Path=/api/auth/**

            - id: task-service
              uri: ${TASK_SERVICE_URL:http://localhost:8082}
              predicates:
                - Path=/api/tasks/**

            - id: ai-assistant-service
              uri: ${AI_ASSISTANT_SERVICE_URL:http://localhost:8086}
              predicates:
                - Path=/api/ai/**

          globalcors:
            cors-configurations:
              '[/**]':
                allowedOrigins:
                  - "http://localhost:4200"
                allowedMethods:
                  - GET
                  - POST
                  - PUT
                  - DELETE
                  - OPTIONS
                allowedHeaders: "*"
                allowCredentials: true
```

---

# Lancement manuel des services

## 1. Auth Service

```bash
cd auth-service
mvn clean install
mvn spring-boot:run
```

Démarrage attendu :

```text
Tomcat started on port 8081
```

---

## 2. Task Service

```bash
cd task-service
mvn clean install
mvn spring-boot:run
```

Démarrage attendu :

```text
Tomcat started on port 8082
```

---

## 3. AI Assistant Service

```bash
cd ai-assistant-service
mvn clean install
mvn spring-boot:run
```

Démarrage attendu :

```text
Tomcat started on port 8086
```

---

## 4. API Gateway

Dans un nouveau terminal Git Bash :

```bash
cd api-gateway

export AUTH_SERVICE_URL=http://localhost:8081
export TASK_SERVICE_URL=http://localhost:8082
export AI_ASSISTANT_SERVICE_URL=http://localhost:8086

mvn clean install
mvn spring-boot:run
```

Démarrage attendu :

```text
Tomcat started on port 8080
```

---

## 5. Frontend Angular

```bash
cd frontend-angular
npm install
ng serve
```

ou :

```bash
npm start
```

Frontend disponible sur :

```text
http://localhost:4200
```

---

# Routes Angular

| Fonctionnalité | URL |
|---|---|
| Login | http://localhost:4200/login |
| Gestion des tâches | http://localhost:4200/tasks |
| Assistant IA | http://localhost:4200/ai-assistant |

---

# Tests API

## Health API Gateway

```bash
curl http://localhost:8080/actuator/health
```

## Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"admin","password":"admin"}'
```

## Création de tâche

```bash
curl -X POST http://localhost:8080/api/tasks \
-H "Authorization: Bearer TOKEN" \
-H "Content-Type: application/json" \
-d '{"title":"Nouvelle tâche"}'
```

## Assistant IA

```bash
curl -X POST http://localhost:8080/api/ai/assistant/ask \
-H "Content-Type: application/json" \
-d '{"question":"Bonjour"}'
```

---

# Lancement automatique de tous les services

Créer un fichier :

```text
start-all.sh
```

à la racine du projet.

## Contenu du script

```bash
#!/bin/bash

export OPENAI_API_KEY="sk-proj-xxxxxxxxxxxxxxxx"

# =========================
# AUTH SERVICE
# =========================
cd auth-service
mvn spring-boot:run &

# =========================
# TASK SERVICE
# =========================
cd ../task-service
mvn spring-boot:run &

# =========================
# AI ASSISTANT SERVICE
# =========================
cd ../ai-assistant-service
mvn spring-boot:run &

# =========================
# API GATEWAY
# =========================
cd ../api-gateway

export AUTH_SERVICE_URL=http://localhost:8081
export TASK_SERVICE_URL=http://localhost:8082
export AI_ASSISTANT_SERVICE_URL=http://localhost:8086

mvn spring-boot:run &

# =========================
# FRONTEND ANGULAR
# =========================
cd ../frontend-angular
ng serve
```

---

# Exécution du script

Sous Git Bash :

```bash
chmod +x start-all.sh
./start-all.sh
```

Tous les services seront démarrés automatiquement.

---

# Architecture finale

```text
Angular Frontend (4200)
          ↓
API Gateway (8080)
          ↓
-------------------------------------------------
Auth Service           → 8081
Task Service           → 8082
AI Assistant Service   → 8086
-------------------------------------------------
```

---

# Fonctionnalités disponibles

## Authentification

- Login JWT
- Protection des routes
- Intercepteur Angular JWT

## Gestion des tâches

- Création de tâche
- Modification
- Suppression
- Liste des tâches

## Intelligence Artificielle

- Assistant IA OpenAI
- Suggestions de tâches
- Planification intelligente
- Aide à l'organisation

---

# Dépannage

## Erreur :

```text
OPENAI_API_KEY est absente
```

Solution :

```bash
export OPENAI_API_KEY="sk-proj-xxxxxxxx"
```

---

## Erreur :

```text
429 insufficient_quota
```

Ajouter du crédit API OpenAI :

https://platform.openai.com/settings/organization/billing/overview

---

## Erreur :

```text
Unknown lifecycle phase clear
```

Utiliser :

```bash
mvn clean install
```

et non :

```bash
mvn clear install
```

---

# Docker

## Build Docker

```bash
docker-compose build
```

## Démarrage Docker

```bash
docker-compose up
```

---

# Kubernetes

Les fichiers Kubernetes sont disponibles dans :

```text
k8s/
```

---

# Auteur

Kwame Agban

GitHub :

https://github.com/kwame-agban

