package src.business;

public abstract class User {
  private long id;
  private String name;
  private String lastName;
  private String registrationNumber;
  private Boolean isDeleted;

  public User() {

  }

  public User(long id, String name, String lastName, String registrationNumber) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.registrationNumber = registrationNumber;
  }

  public User(String name, String lastName, String registrationNumber) {
    this.name = name;
    this.lastName = lastName;
    this.registrationNumber = registrationNumber;
  }

  public abstract void showDetails();

  // --------------- Getters / Setters -----------------------

  public long getId() {
    return this.id;
  }

  public void setId(int value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String value) {
    this.lastName = value;
  }

  public String getRegistrationNumber() {
    return this.registrationNumber;
  }

  public void setRegistrationNumber(String value) {
    this.registrationNumber = value;
  }

    public Boolean getIsDeleted() {
      return this.isDeleted;
    }
    public void setIsDeleted(Boolean value) {
      this.isDeleted = value;
    }
}
