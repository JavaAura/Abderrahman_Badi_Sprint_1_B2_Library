package src.business;

public class Student extends User {
  private String major;
  private String grade;

  public Student() {

  }

  public Student(long id, String name, String lastName, String registrationNumber) {
    super(id, name, lastName, registrationNumber);
  }


  public Student(long id, String name, String lastName, String registrationNumber, String grade, String major) {
    super(id, name, lastName, registrationNumber);
    this.grade = grade;
    this.major = major;
  }

  @Override
  public void showDetails() {
    System.out.println("\n Student Details:");
    System.out.println("\n\t ID: " + getId());
    System.out.println("\n\t Name: " + getName());
    System.out.println("\n\t Last Name: " + getLastName());
    System.out.println("\n\t Registration Number: " + getRegistrationNumber());
    System.out.println("\n\t Major: " + getMajor());
    System.out.println("\n\t Class: " + getGrade());
  }

  // --------------- Getters / Setters -----------------------

  public String getGrade() {
    return this.grade;
  }

  public void setGrade(String value) {
    this.grade = value;
  }

  public String getMajor() {
    return this.major;
  }

  public void setMajor(String value) {
    this.major = value;
  }
}
