package controller;


import dto.request.EmployeeRequest;
import dto.response.APIResponse;
import dto.response.Employee;
import dto.response.EmployeeResponse;
import dto.response.Employees;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

public class EmployeeController {


  private Cookies generateCookies() {
    return RestAssured
        .given()
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .when()
        .get("/employees")
        .then()
        .extract()
        .response()
        .getDetailedCookies();
  }

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

  public Employee getEmployee(int employeeId) {
    Response response = RestAssured
        .given()
        .cookies(generateCookies())
        .baseUri("http://dummy.restapiexample.com")
        .pathParam("employeeId", employeeId)
        .basePath("api/v1")
        .when()
        .get("/employee/{employeeId}");
    response.prettyPrint();
    return response.getBody().as(Employee.class);
  }

  public EmployeeResponse createEmployee(String name, int salary, int age) {
    EmployeeRequest employeeRequest = EmployeeRequest.builder().name(name).salary(salary).age(age)
        .build();
    Response response = RestAssured
        .given()
        .cookies(generateCookies())
        .body(employeeRequest)
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .when()
        .post("/create");
    response.prettyPrint();
    return response.getBody().as(EmployeeResponse.class);
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

  public APIResponse createAndDeleteEmployee(String name, int salary, int age) {
    EmployeeRequest employeeRequest = EmployeeRequest.builder().name(name).salary(salary).age(age)
        .build();
    Response response = RestAssured
        .given()
        .body(employeeRequest)
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .when()
        .post("/create");
    Cookies cookies = response.then().extract().detailedCookies();
    Response responseDelete = RestAssured
        .given()
        .cookies(cookies)
        .baseUri("http://dummy.restapiexample.com")
        .basePath("api/v1")
        .pathParam("employeeId", response.getBody().as(EmployeeResponse.class).getData().getId())
        .when()
        .delete("/delete/{employeeId}");
    responseDelete.prettyPrint();
    return responseDelete.getBody().as(APIResponse.class);
  }
}
