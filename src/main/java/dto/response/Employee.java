package dto.response;

import lombok.Data;

@Data
public class Employee {

  private String status;
  private Data data;

  @lombok.Data
  public static class Data {

    private int id;
    private String name;
    private int salary;
    private int age;
  }
}
