package dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {

  private String name;
  private int salary;
  private int age;
}
