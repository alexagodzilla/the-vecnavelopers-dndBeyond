
# D&Dbeyond Vecnavelopers

**D&Dbeyond Vecnavelopers** is a user-friendly app that enables players to create and customise their characters seamlessly while adhering to the official D&D rules. It leverages the official API for accuracy and consistency.

---

## Features
- Create and manage D&D characters effortlessly
- Intuitive interface for all players
- Full compatibility with official D&D rules

---

## Prerequisites

Before cloning and running this project, ensure you have the following installed:

1. **Java 21**
    - Download and install Java 21 from [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
    - Verify installation by running:
      ```bash
      java -version
      ```  

2. **Maven**
    - Install Maven for dependency management.
    - Follow the installation instructions on the [Maven website](https://maven.apache.org/install.html).
    - Verify installation by running:
      ```bash
      mvn -version
      ```  

3. **PostgreSQL**
    - Install PostgreSQL and ensure it's running.
    - Create the required databases:
      ```bash
      createdb dndbeyond-dev  
      createdb dndbeyond-test  
      ```  

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/alexagodzilla/the-vecnavelopers-dndbeyond.git
   ```  
2. Navigate to the project directory:
   ```bash
   cd the-vecnavelopers-dndbeyond
   ```  
3. Build the project:
   ```bash
   mvn clean install
   ```  
4. Run the application using Spring Boot:
   ```bash
   mvn spring-boot:run
   ```  
5. Access the app in your browser at:
   ```bash
   http://localhost:8080
   ```  

---

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.0**
    - Spring Data JPA
    - Spring Web
    - Spring Thymeleaf
- **PostgreSQL** for the database
- **Flyway** for database migrations
- **OkHttp** for HTTP client functionality
- **Jackson** for JSON data handling
- **Lombok** to reduce boilerplate code
- **Okta** for authentication integration
- **JUnit** and **Mockito** for testing

---

## Dependencies
The main dependencies of the project are listed in the `pom.xml`. Highlights include:
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-thymeleaf`
- `org.springframework.boot:spring-boot-starter-web`
- `org.postgresql:postgresql`
- `com.squareup.okhttp3:okhttp`
- `com.fasterxml.jackson.core:jackson-databind`
- `org.flywaydb:flyway-core`

---

## Testing

In this project, you’ll find two main folders: **controller** and **model**. Each folder contains individual tests located in the following path:

```
the-vecnavelopers-dndbeyond/src/test/java/com/vecnavelopers/dndbeyond
```

### To run the tests manually:

1. **Navigate to the test folder**:
    - Open the `controller` or `model` folder, depending on which tests you want to run.

2. **Run all tests in IntelliJ**:
    - Once inside the folder, click the **green double arrow** button in IntelliJ to run all the tests.

3. **Run individual tests**:
    - To run a specific test, simply click the **single arrow** button next to the individual test.

This way, you have full control over running the tests either in bulk or individually.

---

## Contribution
Feel free to fork this repository and contribute to the project. Submit a pull request with any enhancements or fixes!

---

## License
Nope Sorry :(
