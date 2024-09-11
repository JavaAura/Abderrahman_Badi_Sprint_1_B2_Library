package src.business;

public class Student extends User{
    private String grade;
    private String major;

    public Student() {

    }

    public Student(int id, String name, String lastName, String registrationNumber, String grade, String major) {
        super(id, name, lastName, registrationNumber);
        this.grade = grade;
        this.major = major;
    }

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
