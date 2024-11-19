### Overview
**This is the backend of the Football Project, A RESTful API for managing football games, real-time scores and standings, built with Spring Boot and MySQL.
Users can create games, update the scores of their own games, and view real-time results and league standings for all users across various leagues.
It provides RESTful APIs that the frontend interacts with,
enabling users to perform various football-related operations and also handles user authentication and authorization, ensuring secure access to the system.**

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

### 1. **Spring Boot**
- Spring Boot is used as the main framework to create the RESTful API and handle all backend logic, including dependency injection, transaction management, and more.

### 2. **Hibernate ORM**
- Hibernate is used as the Object-Relational Mapping (ORM) tool to manage database interactions. The application is configured to use Hibernate with MySQL as the database.

### 3. **MySQL**
- MySQL is used as the relational database for storing application data (e.g., users, games, leagues, teams).

### 4. **HikariCP**
- HikariCP is used as the database connection pool provider for efficient database connection management.

### 5. **Spring Data JPA**
- Spring Data JPA is used for simplifying database interactions, though it is not explicitly shown in the `AppConfig` class, it could be used for CRUD operations and simplifying database access.

### 6. **Spring MVC**
- Spring MVC is used as part of Spring Boot to handle the web layer of the application.
  
### 6. **CORS Configuration**
- CORS (Cross-Origin Resource Sharing) is enabled globally in the backend to allow requests from any origin. It is configured via the `WebMvcConfigurer` interface.

### 7. **Flyway (Optional for Database Migrations)**
- Flyway is used for managing and applying database migrations, helping to version control your database schema changes.

### 8. **Java**
- The application requires **Java 17** or higher (OpenJDK or Amazon Corretto). The necessary Java version should be set via the `JAVA_HOME` environment variable.

### 9. **Maven**
- The project is built and managed using **Apache Maven**.

### 10. **Transaction Management**
- Spring’s `@Transactional` annotation is used for automatic transaction management, ensuring that operations like database writes and updates are wrapped in transactions.



