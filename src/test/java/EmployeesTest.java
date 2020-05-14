import controller.EmployeeController;
import dto.response.EmployeeResponse;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.Test;

public class EmployeesTest {

  public static Cookies cookies;

  @Test
  void getEmployees() {
    EmployeeController employeeController = new EmployeeController();
    employeeController.getEmployees();
  }

  @Test
  void getEmployee() {
    EmployeeController employeeController = new EmployeeController();
    employeeController.getEmployee(1);
  }

  @Test
  void createEmployee() {
    EmployeeController employeeController = new EmployeeController();
    employeeController.createEmployee("Dadang", 15000, 25);
  }

  @Test
  void deleteEmployee() {
    EmployeeController employeeController = new EmployeeController();
    employeeController.deleteEmployee(70);
  }

  @Test
  void createAndDeleteEmployee() {
    EmployeeController employeeController = new EmployeeController();
    employeeController.createAndDeleteEmployee("Dadang", 15000, 25);
  }
}
