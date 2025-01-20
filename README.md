# Employees Record Management

## About
This project consisting API CRUD and simple UI for managing employees. It's built using java for the purpose of learning and assignment only.
In this project, I used **_Spring Boot_**, **_Spring Data JPA_**, **_Spring Web MVC_**, **_DataTable_** using Bootstrap 5 for simple UI and applied the best practices for RESTful API.
I would really appreciate any feedback or suggestions for improvement.

Best Regards,
Haris Naufal Rasyid

## Getting Started (How to run the project)
### Prerequisites
- Java 21+
- OpenJDK 21+
- PostgreSQL 14+ or Any other RDBMS
- Git for version control

### Setup Local
1. **Clone this repository**
```bash
https://github.com/harisrsyd/employee-record.git
```
2. **Create a new database in PostgreSQL or you can simply use database.sql file for initialize project database**
```sql 
CREATE DATABASE employee_record;
```
3. **You can either use the dummyData.sql file to populate the database with dummy data or use another method**
4. **Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, etc)**
5. **Change the database configuration in `application.properties`**
6. **Wait until all dependencies are downloaded**

### Run the project
1. **Make sure your java version is 21+**
2. **Build the project to ensure all dependencies are downloaded**
```bash
mvn clean install
```
2. **Run the project**
```bash
mvn spring-boot:run
```
3. **Verify the project is running properly** (default port is 9090)
```bash
curl http://localhost:9090/api/v1/vendors
```
**or you can access the UI in your browser**
```bash
http://localhost:9090
```
4. **You can also access the API documentation in Swagger UI**
```bash
http://localhost:9090/swagger-ui.html
```

## API Endpoints Documentation
You can access the full API endpoint documentation by accessing the **Swagger UI** in your browser.

## User Interface
Simple UI for managing employees record using **DataTable** and **Bootstrap 5**.

## Message From Author
Now, All the CRUD operation and UI are in progress to add more features and improvements. May some feature is not available now, but don't worry I will update it as soon as possible. **Stay tune for the next update!!!!**
