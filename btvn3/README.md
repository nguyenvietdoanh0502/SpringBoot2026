# btvn3

## Run locally

Default profile uses an in-memory H2 database, so you can start the app without installing MySQL.

### Requirements

- Java 21

### Start

```powershell
.\mvnw.cmd spring-boot:run
```

App URLs:

- API base: `http://localhost:8080/api/students`
- Swagger UI: `http://localhost:8080/docs`
- H2 console: `http://localhost:8080/h2-console`

### Run with MySQL

If you already have MySQL running locally, start the app with the `mysql` profile:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

MySQL settings are defined in `src/main/resources/application-mysql.properties`.
