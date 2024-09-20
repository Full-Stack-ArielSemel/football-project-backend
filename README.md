### Overview
**This is the backend of the Football Project, A RESTful API for managing football games, real-time scores and standings, built with Spring Boot and MySQL.
Users can create games, update the scores of their own games, and view real-time results and league standings for all users across various leagues.
It provides RESTful APIs that the frontend interacts with,
enabling users to perform various football-related operations and also handles user authentication and authorization, ensuring secure access to the system.**

### Prerequisites
Before setting up and running the backend of the Football Project, ensure that the following software is installed on your system:

1. **Java Development Kit (JDK)**:
   
    - **version**: 1.8 or higher
    - **Installation**: [Download JDK](https://www.oracle.com/java/technologies/downloads/?er=221886)
      
2. **Maven**:

   - **Version**: 3.6.3 or higher (used for building and managing the Java project)
   - **Installation**: [Download Maven](https://maven.apache.org/download.cgi)

3. **MySQL**:
   
   - **Version**: 5.7 or higher
   - **Installation**: [Download MySQL](https://dev.mysql.com/downloads/installer)
   - **Setup**: Ensure you have a MySQL server running and create a database named 'football_project'

4. **Git (optional, for cloning the repository)**:
   
   - **Installation**: [Download Git](https://git-scm.com/downloads)

6. **IDE (Integrated Development Environment)**:
   
   - **Recommended**: IntelliJ IDEA
   - **installation**: [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows)

### Environment Variables

To run the application, you’ll need to set up the following environment variables:

1. **DB_URL**:
   - **Description**: The URL of the MySQL database
   - **Example**: DB_URL=jdbc:mysql://localhost:3306/football_project

2. **DB_USERNAME**:
   - **Description**: The username for accessing the MySQL database
   - **Example**: DB_USERNAME=root

3. **DB_PASSWORD**:
   - **Description**: The password for accessing the MySQL database
   - **Example**: DB_PASSWORD=1234

4. **JAVA_HOME**:
   - **Description**: The path to the JDK installation
   - **Example**: JAVA_HOME=C:\Program Files\Java\jdk1.8.0_251

5. **MAVEN_HOME**:
   - **Description**: The path to the Maven installation
   - **Example**: MAVEN_HOME=C:\Program Files\Apache\maven

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

