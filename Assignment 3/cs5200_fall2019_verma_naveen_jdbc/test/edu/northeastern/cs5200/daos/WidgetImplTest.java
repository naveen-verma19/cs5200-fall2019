package edu.northeastern.cs5200.daos;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Dtype;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetImplTest {
  WidgetImpl w;

  @Before
  public void setup() {
    w = WidgetImpl.getInstance();
  }

  @Test
  public void test1() {
    Widget web = new YouTubeWidget(999, "yt", null, 14, "style"
            , "class", "text", 0, null, "454", "gg", "gff",
            true, true, Dtype.YOUTUBE);
    w.createWidgetForPage(123, web);
  }

  @Test
  public void test2() {
    Widget web = new ImageWidget(997, "img", 40, 60, null
            , null, null, 3, null, null, "gg", null,
            null, null, Dtype.IMAGE);
    w.createWidgetForPage(123, web);
  }

  @Test
  public void test3() {
    Collection<Widget> res = w.findAllWidgets();
    for (Widget d : res) {
      System.out.println(d.getId() + " "
              + d.getName() + " "
              + d.getName() + " "
              + d.getWidth() + " "
              + d.getHeight() + " "
              + d.getCssClass() + " "
              + d.getCssClass() + " "
              + d.getCssClass() + " "
              + d.getOrder() + " "
              +d.getPage().getId()+" "

      );
    }
  }

  @Test
  public void testForAllPages() {
    Collection<Widget> res = w.findWidgetsForPage(345);
    for (Widget d : res) {
      System.out.println(d.getId() + " "
              + d.getName() + " "
              + d.getName() + " "
              + d.getWidth() + " "
              + d.getHeight() + " "
              + d.getCssClass() + " "
              + d.getCssClass() + " "
              + d.getCssClass() + " "
              + d.getOrder() + " "
              +d.getPage().getId()+" "

      );
    }
  }

  @Test
  public void test4() {
    Widget web = new ImageWidget(567, "img2", 40, 60, null
            , null, null, 3, null, null, "gg", null,
            null, null, Dtype.IMAGE);
    Page p = new Page(123, "ghghg", "an online social media and social networking service", null, null, 1234234);

    web.setPage(p);

    w.updateWidget(997,web);
  }

  @Test
  public void test5() {
    Widget d =w.findWidgetById(234);
    System.out.println(d.getId() + " "
            + d.getName() + " "
            + d.getName() + " "
            + d.getWidth() + " "
            + d.getHeight() + " "
            + d.getCssClass() + " "
            + d.getCssClass() + " "
            + d.getCssClass() + " "
            + d.getOrder() + " "
            +d.getPage().getId()+" "
    );
  }

  @Test
  public void test6() {
    w.deleteWidget(998);
  }

}