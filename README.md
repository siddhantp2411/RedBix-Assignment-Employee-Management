#Api endpoint documentation : https://documenter.getpostman.com/view/30640565/2sA2r6YjbG

Task Statement: Spring Boot Employee Table Read-Write Access Assignment
 To run and test the Spring Boot Employee Management API project, follow these steps:
Prerequisites:
1.	Java Development Kit (JDK) installed on your system.
2.	Maven installed on your system.
1.	Navigate to the Project Directory:
cd RedBixAssigment.
2.  Build the Project:
Use Maven to build the project. Run the following command:
 mvn clean install

3. Run the Application: 
After the build is successful, you can run the application using the following command:
mvn spring-boot:run

4. Test the Endpoints: 

the application is running, you can test the endpoints using tools like Postman or cURL.

To retrieve all employees:
GET http://localhost:8080/employees

To retrieve a specific employee by ID:

GET   http://localhost:8080/employees/{id}

•	To add a new employee: 
 POST http://localhost:8080/employees
Body:
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "designation": "Software Engineer"
}


•	To update an existing employee:
PUT http://localhost:8080/employees/{id}
Body:
{
    "firstName": "UpdatedFirstName",
    "lastName": "UpdatedLastName",
    "email": "updated.email@example.com",
    "designation": "Senior Software Engineer"
} 

