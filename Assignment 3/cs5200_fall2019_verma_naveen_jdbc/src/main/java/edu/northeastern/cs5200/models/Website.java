package edu.northeastern.cs5200.models;


import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class Website {
  private int id;
  private String name;
  private String description;
  private Date created;
  private Date updated;
  private int visits;
  private Developer owner;

  private Collection<Role> roles;
  private Collection<Priviledge> priviledges;
  private Collection<Page> pages;

  public Website() {
  }

  public Website(int id, String name, String description, Date created, Date updated, int visits) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.created = created;
    this.updated = updated;
    this.visits = visits;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public int getVisits() {
    return visits;
  }

  public void setVisits(int visits) {
    this.visits = visits;
  }

  public Developer getOwner() {
    return owner;
  }

  public void setOwner(Developer owner) {
    this.owner = owner;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }

  public Collection<Priviledge> getPriviledges() {
    return priviledges;
  }

  public void setPriviledges(Collection<Priviledge> priviledges) {
    this.priviledges = priviledges;
  }

  public Collection<Page> getPages() {
    return pages;
  }

  public void setPages(Collection<Page> pages) {
    this.pages = pages;
  }
}
