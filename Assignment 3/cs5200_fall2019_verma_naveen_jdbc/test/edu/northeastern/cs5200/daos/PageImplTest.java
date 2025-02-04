package edu.northeastern.cs5200.daos;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

public class PageImplTest {
  PageImpl w;

  @Before
  public void setup() {
    w = PageImpl.getInstance();
  }

  @Test
  public void test1() {
    Page page = new Page(999, "pagemm", "an online social media and social networking service", null, null, 1234234);
    w.createPageForWebsite(123, page);
  }

  @Test
  public void test2() {
    Collection<Page> res = w.findAllPages();
    for (Page d : res) {
      System.out.println(d.getId() + " "
              + d.getId() + " "
              + d.getTitle() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getViews() + " "
              +d.getWebsite().getId()+" "
      );
    }
  }

  @Test
  public void test3() {
   Page d = w.findPageById(123);

      System.out.println(d.getId() + " "
              + d.getTitle() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getViews() + " "
              +d.getWebsite().getId()+" "
      );

  }

  @Test
  public void test4() {
    Collection<Page> res = w.findPagesForWebsite(567);
    for (Page d : res) {
      System.out.println(d.getId() + " "
              + d.getId() + " "
              + d.getTitle() + " "
              + d.getDescription() + " "
              + d.getCreated() + " "
              + d.getUpdated() + " "
              + d.getViews() + " "
              +d.getWebsite().getId()+" "
      );
    }
  }
//
  @Test
  public void test5() {
    Page web = new Page( 123, "ghghg", "an online social media and social networking service", null, null, 1234234);
    web.setWebsite(WebsiteImpl.getInstance().findWebsiteById(123));
    web.setTitle("ghg");
    w.updatePage(123, web);
  }

  @Test
  public void test6() {

    w.deletePage(123);
  }

}
