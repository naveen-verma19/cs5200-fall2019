package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class Developer extends Person{
  private String developerKey;

  private List<Role> websiteRoles;
  private List<Role> pageRoles;
  private List<Priviledge> websitePriviledges;
  private List<Priviledge> pagePriviledges;

  public List<Role> getWebsiteRoles() {
    return websiteRoles;
  }

  public void setWebsiteRoles(List<Role> websiteRoles) {
    this.websiteRoles = websiteRoles;
  }

  public List<Role> getPageRoles() {
    return pageRoles;
  }

  public void setPageRoles(List<Role> pageRoles) {
    this.pageRoles = pageRoles;
  }

  public List<Priviledge> getWebsitePriviledges() {
    return websitePriviledges;
  }

  public void setWebsitePriviledges(List<Priviledge> websitePriviledges) {
    this.websitePriviledges = websitePriviledges;
  }

  public List<Priviledge> getPagePriviledges() {
    return pagePriviledges;
  }

  public void setPagePriviledges(List<Priviledge> pagePriviledges) {
    this.pagePriviledges = pagePriviledges;
  }

  //a
  public Developer(String developerKey, int id, String firstName, String lastName) {
    super(id, firstName, lastName, null, null, null, null);
    this.developerKey = developerKey;
  }

  //b
  public Developer( String developerKey, int id, String firstName, String lastName, String username, String password, String email, Date dob) {
    super(id, firstName, lastName, username, password, email, dob);
    this.developerKey = developerKey;
  }

  //c
  public Developer(String developerKey, int id, String firstName, String lastName, String username, String password,
                   String email, Date dob, Collection<Address> addresses, Collection<Phone> phones) {

    super(id, firstName, lastName, username, password, email, dob);
    this.developerKey = developerKey;
    this.setAddresses(addresses);
    this.setPhones(phones);
  }

  public String getDeveloperKey() {
    return developerKey;
  }

  public void setDeveloperKey(String developerKey) {
    this.developerKey = developerKey;
  }

}
