import controller.EmployeeController;
import dto.response.Employee;
import org.junit.jupiter.api.Test;

public class EmployeesTest {

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
    employeeController.deleteEmployee(93);
  }

  @Test
  void createAndDeleteEmployee() {
    EmployeeController employeeController = new EmployeeController();
    Employee employee = employeeController.createEmployee("Dadang", 15000, 25);
    employeeController.deleteEmployee(employee.getData().getId());
  }
}
