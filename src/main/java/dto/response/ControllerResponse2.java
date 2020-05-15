package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class ControllerResponse2 {

  private String status;
  private Data data;

  @lombok.Data
  public static class Data {

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
