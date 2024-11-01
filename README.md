# Glofox Microservices
## Setup
- Run `mvn clean install` to build the application.
- Run the application using `java -jar target/glofox-app.jar`.
- To run with Docker:
  - `docker build -t glofox-app .`
  - `docker run -p 8080:8080 glofox-app`
## Endpoints
- POST `/api/v1/classes`: Creates a class.
- POST `/api/v1/bookings`: Books a class.
