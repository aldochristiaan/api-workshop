import controller.Controller;
import dto.response.APIResponse;
import dto.response.ControllerResponse;
import dto.response.ControllerResponse2;
import dto.response.EmployeeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ControllerTest {

  @Test
  @Tag("ControllerTest")
  @DisplayName("Contoh Test")
  public void testGetEmployees() {
    Controller controller = new Controller();
    ControllerResponse controllerResponse = controller.getEmployees();
    Assertions.assertNotNull(controllerResponse);
    Assertions.assertEquals("success", controllerResponse.getStatus());
  }

  @Test
  public void getEmployee() {
    Controller controller = new Controller();
    ControllerResponse2 employeeByIdResponse = controller.getEmployee(1);
    Assertions.assertNotNull(employeeByIdResponse);
    Assertions.assertEquals("success", employeeByIdResponse.getStatus());
  }

  @Test
  public void createEmployee() {
    Controller controller = new Controller();
    controller.createEmployee("Bebas", 15000, 25);
  }

  @Test
  public void createAndUpdateEmployee() {
    Controller controller = new Controller();
    String currentName = "Test";
    int currentSalary = 15000;
    int currentAge = 25;
    ControllerResponse2 updateEmployeeResponse = controller
        .updateEmployee(currentName, currentSalary, currentAge, "Aldo", 16000, 26);
    Assertions.assertNotEquals(currentName, updateEmployeeResponse.getData().getEmployeeName());
    Assertions.assertNotEquals(currentSalary, updateEmployeeResponse.getData().getEmployeeSalary());
    Assertions.assertNotEquals(currentAge, updateEmployeeResponse.getData().getEmployeeAge());
  }

  @Test
  public void createAndDeleteEmployee() {
    Controller controller = new Controller();
    String currentName = "Baru";
    int currentSalary = 15000;
    int currentAge = 25;
    APIResponse deleteEmployeeResponse = controller
        .createAndDeleteEmployee(currentName, currentSalary, currentAge);
  }
}
