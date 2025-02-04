package edu.northeastern.cs5200.models;

public class Phone {
  private Person person;
  private String phone;
  private boolean primary;

  public Phone(Person person, String phone, boolean primary) {
    this.person = person;
    this.phone = phone;
    this.primary = primary;
  }

  public Phone() {
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }
}
