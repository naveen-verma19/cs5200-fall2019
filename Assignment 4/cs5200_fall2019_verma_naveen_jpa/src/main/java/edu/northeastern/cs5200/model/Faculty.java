package edu.northeastern.cs5200.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.northeastern.cs5200.model.Course;

@Entity
@Table(name="student")
public class Faculty extends User{
  private String office;
  private Boolean tenured;

  @OneToMany(mappedBy = "faculty")
  private List<Course> courses;

  @OneToMany(mappedBy = "faculty")
  private List<Section> sections;

  public Faculty() {
    super();
  }

  public Faculty(String firstName, String lastName, String office, Boolean tenured) {
    super(firstName.toLowerCase(),"password",firstName,lastName);
    this.office = office;
    this.tenured = tenured;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public Boolean getTenured() {
    return tenured;
  }

  public void setTenured(Boolean tenured) {
    this.tenured = tenured;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }


}
