package src.business;

public class Professor extends User {
  private String department;

  public Professor() {

  }

  public Professor(String name, String lastName, String registrationNumber, String department) {
    super(name, lastName, registrationNumber);
    this.department = department;
  }

  public Professor(long id, String name, String lastName, String registrationNumber, String department) {
    super(id, name, lastName, registrationNumber);
    this.department = department;
  }

  @Override
  public void showDetails() {
    System.out.println("\n Professor Details:");
    System.out.println("\n\t ID: " + getId());
    System.out.println("\n\t Name: " + getName());
    System.out.println("\n\t Last Name: " + getLastName());
    System.out.println("\n\t Registration Number: " + getRegistrationNumber());
    System.out.println("\n\t Department: " + getDepartment());
  }

  // --------------- Getters / Setters -----------------------

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String value) {
    this.department = value;
  }
}
