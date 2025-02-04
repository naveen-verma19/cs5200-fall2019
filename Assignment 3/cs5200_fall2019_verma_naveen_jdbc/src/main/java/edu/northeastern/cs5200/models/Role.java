package edu.northeastern.cs5200.models;

public class Role {
  private Website website;
  private Developer developer;
  private Page page;
  private String role;



  public Role() {
  }

  /**
   * Create website role
   */
  public Role(Developer developer, Website website, String role) {
    this.website = website;
    this.developer = developer;
    this.role = role;
    this.page= null;
  }

  /**
   * Create page role
   */
  public Role(Developer developer, Page page, String role) {
    this.developer = developer;
    this.page = page;
    this.role = role;
    this.website= null;
  }

  public Website getWebsite() {
    return website;
  }

  public void setWebsite(Website website) {
    this.website = website;
  }

  public Developer getDeveloper() {
    return developer;
  }

  public void setDeveloper(Developer developer) {
    this.developer = developer;
  }

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
