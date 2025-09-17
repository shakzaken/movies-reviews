# Movie Review API

## Overview

This application is a backend service for managing movies and reviews, built with Spring Boot and MongoDB. It allows users to register, and movies have information such as title, genre, release date, director, cast, and embedded reviews. Users can perform CRUD operations on movies and reviews. The application also supports analytics-style operations using MongoDB aggregation (e.g., average ratings, grouping by genre, counting reviews over time). Indexes are used to optimize query performance.

---

## Technologies

- **Spring Boot**  
  Used to build the REST API, manage controllers, services, repositories, dependency injection, configuration, and more.

- **MongoDB**  
  Document-oriented NoSQL database to store `users`, `movies`, and embedded `reviews`.

- **Aggregation**  
  Using MongoDB’s aggregation pipeline to support operations like:
    - computing average rating per movie
    - getting top rated movies
    - grouping movies by genre with total reviews and average rating
    - computing review counts over time (e.g., by month)

- **Indexes**  
  For performance improvement and constraint enforcement:
    - single‑field indexes (e.g. on `genre`, `users.email`, `reviews.rating`, `reviews.reviewDate`)
    - unique index on `users.email`
    - compound indexes (e.g. `genre + releaseDate`) to support filtering + sorting combinations

---

## API

Here are the main endpoints:

| Resource  | Method | Endpoint                                           | Description |
|-----------|--------|-----------------------------------------------------|-------------|
| **Users** | `POST` | `/api/users`                                       | Create a new user. Body: `{ "name": "...", "email": "..." }` |
|           | `DELETE` | `/api/users/{userId}`                           | Delete a user by ID |
| **Movies** | `POST` | `/api/movies`                                     | Create a new movie. Body includes: `title`, `genre`, `releaseDate`, `director`, `cast` |
|           | `PUT`  | `/api/movies/{movieId}`                          | Update an existing movie's info |
|           | `DELETE` | `/api/movies/{movieId}`                        | Delete a movie by ID |
|           | `GET`  | `/api/movies/{movieId}`                         | Get details of a movie by ID |
| **Reviews** | `POST` | `/api/movies/{movieId}/reviews`               | Add a review to a movie. Body: `{ "userId", "rating", "comment", "reviewDate" }` |
|           | `DELETE` | `/api/movies/{movieId}/reviews/{reviewId}`     | Delete a specific review by its ID |

### Aggregation / Analytics Endpoints 

These are additional endpoints you may have:

- `GET /api/query/rated-movies` — return top N movies by average rating
- `GET /api/query/movies-by-genre` — group movies by genre, with each genre’s average rating and review count
- `GET /api/query/users-watched-movies` — users who have written more than N reviews
- `GET /api/query/monthly-review-count` — number of reviews per month over the past year

---

## How to Run the Application

### Prerequisites

- Java JDK (e.g. version 11 or newer)
- Build tool: Maven or Gradle
- MongoDB instance (local or remote)

### Steps

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd <your-repo-folder>

## Configure MongoDB connection
In src/main/resources/application.properties (or application.yml), set up the MongoDB URI and database name. Example:

spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=movie_reviews_db
### If using authentication, also set username/password properties

## Build the application
``
./gradlew build
``
## Run the application
``
./gradlew bootRun
``

## Access the API
By default the application runs on port 8080 (unless configured otherwise). Use Postman or similar tools to test endpoints, for example:


```
 http://localhost:8080/api/users
 http://localhost:8080/api/movies
