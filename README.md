## Table of Contents

1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Database Setup](#database-setup)
4. [Running the Project](#running-the-project)
5. [Tools and Technologies Used](#tools-and-technologies-used)

### Overview
**This is the backend of the Football Project, A RESTful API for managing football games, real-time scores and standings, built with Spring Boot and MySQL.
Users can create games, update the scores of their own games, and view real-time results and league standings for all users across various leagues.
It provides RESTful APIs that the frontend interacts with,
enabling users to perform various football-related operations and also handles user authentication and authorization, ensuring secure access to the system.**

**For the frontend implementation of this project, visit the [Frontend Repository]([https://github.com/your-frontend-repo-link](https://github.com/Full-Stack-ArielSemel/football-project-frontend)).**

### Prerequisites
Before setting up and running this backend, ensure that the following software is installed on your system:

1. **MySQL 8.0 (or higher)**:
    - **Installation**: [Download MySQL](https://dev.mysql.com/downloads/installer)
      
2. **Java 17 (or higher)**:
   - **Installation**: [Download JDK](https://www.oracle.com/java/technologies/downloads/?er=221886)
   - **Example Setting Enviroment Variable on Windows:**
     ```bash
        set JAVA_HOME=C:\path\to\your\jdk
        set PATH=%JAVA_HOME%\bin;%PATH%
   - **Example Setting Enviroment Variable on macOS/Linux:**
      ```bash
         export JAVA_HOME=/path/to/your/jdk
         export PATH=$JAVA_HOME/bin:$PATH

3. **Apache Maven 3.9.6 (or higher)**:
   - **Installation**: [Download Maven](https://maven.apache.org/download.cgi)
   - **Example for setting Enviroment Variable on Windows:**
     ```bash
        set MAVEN_HOME=C:\path\to\maven
        set PATH=%MAVEN_HOME%\bin;%PATH%
   - **Example for setting Enviroment Variable on macOS/Linux:**
     ```bash
        export MAVEN_HOME=/path/to/maven
        export PATH=$MAVEN_HOME/bin:$PATH

### Database Setup
1. **Create a new Database:**
   ```bash
   CREATE DATABASE your_db_name;
   
2. **Configure Database Credentials:**
   - Download the .env.sample file and rename it to .env in the root directory of your project.
   - Update the .env file with your database credentials and any other environment variables required by the application (e.g., API keys, secret keys).
     
3. **Flyway Database Migration:**
   - Flyway is already set up in the project to manage database schema migrations. The migration scripts are located in src/main/resources/db/migration/
   - **Automatic Migration**: When you run the application, Flyway will automatically apply any pending migrations to the database.
   - **Manual Migration**: Alternatively, you can manually run migrations with this Maven command:
     ```bash
     mvn flyway:migrate
     
### Running the project

1. **Navigate to the project directory:**
   ```bash
      cd /path/to/your/project
   
2. **Run the project using Maven:**
   ```bash
      mvn clean install
      mvn spring-boot:run

### Tools and Technologies Used

- **Java & Spring Boot:**
    Core technologies for backend development, providing a robust and scalable foundation.

- **Spring Data JPA:** Simplifies database interactions using Java Persistence API (JPA) for efficient data management.

- **Spring MVC:** Web framework for building RESTful APIs with a model-view-controller architecture.
  
- **RESTful APIs:** Experience in designing and developing REST APIs within the Spring ecosystem, following best practices for stateless, scalable, and secure endpoints.
  
- **MySQL:** Relational database used for data storage and management.

- **Flyway:** Database migration tool for version control of schemas and automated migrations.

- **Hibernate ORM (JPA):** Manages database operations and object-relational mapping (ORM) to persist Java objects.
  
- **Transaction Management:** ensures atomic operations, preventing inconsistent database states.

- **HikariCP:** High-performance connection pool for efficient database connection management.

- **Maven:** A build and dependency management tool for Java projects, automating tasks such as compilation, testing, packaging, and dependency resolution.

- **Git**: Version control system for code collaboration and management.

- **Postman:** Tool for testing and interacting with REST APIs.

- **JUnit:** Framework for unit testing Java code to ensure robustness and correctness.
