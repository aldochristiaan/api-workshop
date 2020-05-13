package controller;


import dto.request.EmployeeRequest;
import dto.response.APIResponse;
import dto.response.Employee;
import dto.response.Employees;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeeController {

  public Employees getEmployees() {
    Response response = RestAssured
        .given()
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .when()
        .get("/employees");
    response.prettyPrint();
    return response.getBody().as(Employees.class);
  }

  public Employees getEmployee(int employeeId) {
    Response response = RestAssured
        .given()
        .baseUri("http://dummy.restapiexample.com")
        .pathParam("employeeId", employeeId)
        .basePath("api/v1")
        .when()
        .get("/employee/{employeeId}");
    response.prettyPrint();
    return response.getBody().as(Employees.class);
  }

  public Employee createEmployee(String name, int salary, int age) {
    EmployeeRequest employeeRequest = EmployeeRequest.builder().name(name).salary(salary).age(age)
        .build();
    Response response = RestAssured
        .given()
        .body(employeeRequest)
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .when()
        .post("/create");
    response.prettyPrint();
    return response.getBody().as(Employee.class);
  }

  public APIResponse deleteEmployee(int employeeId) {
    Response response = RestAssured
        .given()
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .pathParam("employeeId", employeeId)
        .when()
        .delete("/delete/{employeeId}");
    response.prettyPrint();
    return response.getBody().as(APIResponse.class);
  }
}
