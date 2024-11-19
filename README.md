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

### Running the project

- Navigate to the project directory : cd /path/to/your/project

- Run the project using Maven:
   - mvn clean install
   - mvn spring-boot:run

### Key Features

 1. **RESTful API with Spring Boot**: Provides a robust backend for managing football matches, teams, leagues, and users with comprehensive CRUD operations
    
 2. **MySQL Database Integration**: Stores all match, team, league, and user data, ensuring persistent and reliable data management
    
 3. **Hibernate ORM**: Utilizes Hibernate for Object-Relational Mapping (ORM), simplifying database interactions and enhancing data handling efficiency
    
 4. **Real-Time Updates**: Supports real-time updates for live match scores and league tables, keeping users informed with the latest information
    
 5. **User Authentication and Authorization**: Ensures secure access to the application, allowing users to register, log in, and manage their data securely
     
 6. **Efficient Data Management**: Optimizes data queries and transactions, providing fast and reliable performance for all backend operations

