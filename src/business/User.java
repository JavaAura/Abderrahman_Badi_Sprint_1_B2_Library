package src.business;

public class User {
    private long id;
    private String name;
    private String lastName;
    private String registrationNumber;

    public User() {

    }

    public User(long id, String name, String lastName, String registrationNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
    }

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
}
