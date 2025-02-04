package edu.northeastern.cs5200.models;

public class Priviledge {
  private Website website;
  private Developer developer;
  private Page page;
  private String priviledge;

  public Priviledge() {
  }


  /**
   * create website priviledge
   * */
  public Priviledge(Website website, Developer developer, String priviledge) {
    this.website = website;
    this.developer = developer;
    this.priviledge = priviledge;
  }

  /**
   * create page priviledge
   * */
  public Priviledge(Developer developer, Page page, String priviledge) {
    this.developer = developer;
    this.page = page;
    this.priviledge = priviledge;
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

  public String getPriviledge() {
    return priviledge;
  }

  public void setPriviledge(String priviledge) {
    this.priviledge = priviledge;
  }
}
