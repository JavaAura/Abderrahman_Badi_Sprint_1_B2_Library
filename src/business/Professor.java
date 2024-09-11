package src.business;

public class Professor extends User {
  private String department;

  public Professor() {

  }

  public Professor(int id, String name, String lastName, String registrationNumber, String department) {
    super(id, name, lastName, registrationNumber);
    this.department = department;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String value) {
    this.department = value;
  }
}
