package controller;

import dto.request.EmployeeRequest;
import dto.response.APIResponse;
import dto.response.ControllerResponse;
import dto.response.ControllerResponse2;
import dto.response.EmployeeResponse;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

public class Controller {

  public ControllerResponse getEmployees() {
    Response getEmployeesResponse = RestAssured
        .given()
        .when()
        .get("http://dummy.restapiexample.com/api/v1/employees");

    return getEmployeesResponse.getBody().as(ControllerResponse.class);
  }

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

  public ControllerResponse2 getEmployee(int id) {
    Response getEmployeeResponse = RestAssured
        .given()
        .cookies(generateCookies())
        .pathParam("employeeId", id)
        .when()
        .get("http://dummy.restapiexample.com/api/v1/employee/{employeeId}");

    return getEmployeeResponse.getBody().as(ControllerResponse2.class);
  }

  public EmployeeResponse createEmployee(String name, int salary, int age) {
    EmployeeRequest employeeRequest = EmployeeRequest
        .builder()
        .name(name)
        .salary(salary)
        .age(age)
        .build();

    Response createEmployeeResponse = RestAssured
        .given()
        .body(employeeRequest)
        .when()
        .post("http://dummy.restapiexample.com/api/v1/create");

    createEmployeeResponse.prettyPrint();

    return createEmployeeResponse.getBody().as(EmployeeResponse.class);
  }

  public ControllerResponse2 updateEmployee(String currentName, int currentSalary, int currentAge,
      String newName,
      int newSalary, int newAge) {

    // Create Employee First
    EmployeeRequest employeeRequest = EmployeeRequest
        .builder()
        .name(currentName)
        .salary(currentSalary)
        .age(currentAge)
        .build();

    Response createEmployeeResponse = RestAssured
        .given()
        .body(employeeRequest)
        .when()
        .post("http://dummy.restapiexample.com/api/v1/create");

    // Get cookies from create employee
    Cookies cookies = createEmployeeResponse.then().extract().detailedCookies();

    EmployeeRequest employeeUpdateRequest = EmployeeRequest
        .builder()
        .name(newName)
        .salary(newSalary)
        .age(newAge)
        .build();

    Response updateEmployeeResponse = RestAssured
        .given()
        .cookies(cookies)
        .body(employeeUpdateRequest)
        .pathParam("employeeId",
            createEmployeeResponse.getBody().as(EmployeeResponse.class).getData().getId())
        .when()
        .put("http://dummy.restapiexample.com/api/v1/update/{employeeId}");

    updateEmployeeResponse.prettyPrint();

    return updateEmployeeResponse.getBody().as(ControllerResponse2.class);
  }

  public APIResponse createAndDeleteEmployee(String currentName, int currentSalary, int currentAge) {

    // Create Employee First
    EmployeeRequest employeeRequest = EmployeeRequest
        .builder()
        .name(currentName)
        .salary(currentSalary)
        .age(currentAge)
        .build();

    Response createEmployeeResponse = RestAssured
        .given()
        .body(employeeRequest)
        .when()
        .post("http://dummy.restapiexample.com/api/v1/create");

    createEmployeeResponse.prettyPrint();

    // Get cookies from create employee
    Cookies cookies = createEmployeeResponse.then().extract().detailedCookies();

    Response deleteEmployeeResponse = RestAssured
        .given()
        .cookies(cookies)
        .pathParam("employeeId",
            createEmployeeResponse.getBody().as(EmployeeResponse.class).getData().getId())
        .when()
        .delete("http://dummy.restapiexample.com/api/v1/delete/{employeeId}");

    deleteEmployeeResponse.prettyPrint();

    return deleteEmployeeResponse.getBody().as(APIResponse.class);
  }
}
