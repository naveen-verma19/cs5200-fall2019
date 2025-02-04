package edu.northeastern.cs5200.models;

import java.sql.Date;

public class User extends Person{
  private Boolean userAgreement;

  public boolean isUserAgreement() {
    return userAgreement;
  }

  public void setUserAgreement(boolean userAgreement) {
    this.userAgreement = userAgreement;
  }

  public User(int id, String firstName, String lastName) {
    super(id, firstName, lastName, null, null, null, null);
    this.userAgreement=null;
  }

  public User(int id, String firstName, String lastName, Boolean userAgreement) {
    super(id, firstName, lastName, null, null, null, null);
    this.userAgreement=userAgreement;
  }

  public User(int id, String firstName, String lastName, String username, String password, String email, Date dob, Boolean userAgreement) {
    super(id, firstName, lastName, username, password, email, dob);
    this.userAgreement = userAgreement;
  }

  public User() {
    super();
  }
}
