package edu.northeastern.cs5200.daos;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Website;

public class WebsiteImplTest {
  WebsiteImpl w;

  @Before
  public void setup() throws SQLException, ClassNotFoundException {
    w = WebsiteImpl.getInstance();
  }

  @Test
  public void test1() {
    Website web = new Website(999, "Facebook2", "an online social media and social networking service", null, null, 1234234);
    w.createWebsiteForDeveloper(12, web);
  }

  @Test
  public void test2() {
    Collection<Website> res = w.findAllWebsites();
    for (Website d : res) {
      System.out.println(d.getId() + " "
              + d.getId() + " "
              + d.getName() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getVisits() + " "
      );
    }
  }

  @Test
  public void test3() {
    Collection<Website> res = w.findWebsitesForDeveloper(12);
    for (Website d : res) {
      System.out.println(d.getId() + " "
              + d.getId() + " "
              + d.getName() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getVisits() + " "
      );
    }
  }

  @Test
  public void test4() {
    Website d = w.findWebsiteById(123);

      System.out.println(d.getId() + " "
              + d.getId() + " "
              + d.getName() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getVisits() + " "
      );
  }

  @Test
  public void test5() {
    Website web = new Website(123, "Facebook2", "an online social media and social networking service", null, null, 1234234);
    web.setName("facebook3");
    web.setVisits(12345);
    w.updateWebsite(123, web);
  }

  @Test
  public void test6() {

    w.deleteWebsite(999);
  }

}
