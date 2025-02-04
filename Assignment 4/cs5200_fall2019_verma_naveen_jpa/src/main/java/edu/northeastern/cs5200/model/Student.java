package edu.northeastern.cs5200.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student extends User {
  private int gradYear;
  private long scholarship;

  @OneToMany(mappedBy = "student")
  List<Enrollment> enrollments;

  public Student() {
  }

  public Student(String firstName, String lastName, int gradYear, long scholarship) {
    super(firstName.toLowerCase(),"password", firstName, lastName);
    this.gradYear = gradYear;
    this.scholarship = scholarship;
  }

  public int getGradYear() {
    return gradYear;
  }

  public void setGradYear(int gradYear) {
    this.gradYear = gradYear;
  }

  public long getScholarship() {
    return scholarship;
  }

  public void setScholarship(long scholarship) {
    this.scholarship = scholarship;
  }

  public List<Enrollment> getEnrollments() {
    return enrollments;
  }

  public void setEnrollments(List<Enrollment> enrollments) {
    this.enrollments = enrollments;
  }
}
