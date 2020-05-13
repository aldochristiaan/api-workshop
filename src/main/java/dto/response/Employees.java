package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Employees {

  private String status;
  private List<Data> data;

  @lombok.Data
  private static class Data {

    private int id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private int employeeSalary;
    @JsonProperty("employee_age")
    private int employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;
  }
}
