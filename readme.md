# SpringMarket

SpringMarket is a project that I made with the objective of learn several features from the Spring family.

There's no demo online, but a preview can be seen at my [Portfolio](https://mbarcina001.github.io/Portfolio)

- Spring Boot
- Spring Security
- Spring JPA
- Hibernate + MySQL
- [HikariCP](https://github.com/brettwooldridge/HikariCP) - A solid high-performance JDBC connection pool
- Thymeleaf

## Running the project

#### Prerequisites

- [Java 8](https://www.java.com/es/download/)
- [MySQL](https://www.mysql.com/)

#### Installation

1. Clone the project.
2. Import spring_market database from src/main/resources/static/sql/bbdd.sql to MySQL.
3. Open Java project as Maven project with your favorite IDE.
4. Add your database configuration to src/main/resources/application.properties
    1. A servlet is provided for db testing purposes at src/main/java/com.mbarcina.testdb.testDbServlet.java
5. Start the application at src/main/java/com.mbarcina.springmarket.SpringmarketApplication
