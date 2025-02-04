package edu.northeastern.cs5200.daos;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Developer;

public class DeveloperImplTest {
  DeveloperImpl c;

  @Before
  public void setup() {
    c = DeveloperImpl.getInstance();
  }

  @Test
  public void test1() {
    Developer d = new Developer("4321rewq",12, "Alice", "Wonder", "alice"
            , "alice", "alice@wonder.com", null);
    c.createDeveloper(d);
  }

  @Test
  public void test2() {
    Collection<Developer> res = c.findAllDevelopers();
    for (Developer d : res) {
      System.out.println(d.getId() + " "
              + d.getFirstName() + " "
              + d.getLastName() + " "
              + d.getUsername() + " "
              + d.getPassword() + " "
              + d.getDeveloperKey() + " "
              + d.getEmail() + " "
              + d.getDob() + " "
      );
    }
  }

  @Test
  public void test3() {
    Developer d = c.findDeveloperById(12);

    System.out.println(d.getId() + " "
            + d.getFirstName() + " "
            + d.getLastName() + " "
            + d.getUsername() + " "
            + d.getPassword() + " "
            + d.getDeveloperKey() + " "
            + d.getEmail() + " "
            + d.getDob() + " "
    );

  }

  @Test
  public void test4() {
    Developer d = c.findDeveloperByUsername("charlie");

    System.out.println(d.getId() + " "
            + d.getFirstName() + " "
            + d.getLastName() + " "
            + d.getUsername() + " "
            + d.getPassword() + " "
            + d.getDeveloperKey() + " "
            + d.getEmail() + " "
            + d.getDob() + " "
    );

  }

  @Test
  public void test5() {
    Developer d = c.findDeveloperByCredentials("charlie", "charlie");

    System.out.println(d.getId() + " "
            + d.getFirstName() + " "
            + d.getLastName() + " "
            + d.getUsername() + " "
            + d.getPassword() + " "
            + d.getDeveloperKey() + " "
            + d.getEmail() + " "
            + d.getDob() + " "
    );

  }

  @Test
  public void test6() {

    Developer d = new Developer("4321rewq",99, "fn", "ln", "fn"
            , "fn", "a@b.com", null);
//    c.createDeveloper(d);
    d.setFirstName("fn4");
    d.setDeveloperKey("changed");
    c.updateDeveloper(12, d);

    System.out.println(d.getId() + " "
            + d.getFirstName() + " "
            + d.getLastName() + " "
            + d.getUsername() + " "
            + d.getPassword() + " "
            + d.getDeveloperKey() + " "
            + d.getEmail() + " "
            + d.getDob() + " "
    );

  }

  @Test
  public void test7() {

    c.deleteDeveloper(12);
  }

}