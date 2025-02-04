package edu.northeastern.cs5200;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.northeastern.cs5200.daos.AddressDoa;
import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.DeveloperImpl;
import edu.northeastern.cs5200.daos.PageImpl;
import edu.northeastern.cs5200.daos.PhoneDao;
import edu.northeastern.cs5200.daos.RoleDao;

import edu.northeastern.cs5200.daos.RoleImpl;
import edu.northeastern.cs5200.daos.UserDao;
import edu.northeastern.cs5200.daos.WebsiteDao;
import edu.northeastern.cs5200.daos.WebsiteImpl;
import edu.northeastern.cs5200.daos.WidgetImpl;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Dtype;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class hw_jdbc_last_first {

  int aliceId;
  int bobId;
  int charlieId;
  long millis;
  Website fb;
  Website twitter;
  Website wikipedia;
  Website cnn;
  Website cnet;
  Website gizmodo;
  java.sql.Date date;
  WebsiteDao d;
  RoleDao roleDao;
  int fbId;
  int twitterId;
  int wikiId;
  int cnnId;
  int cnetIid;
  int gizId;

  @Before
  public void setup() {
    aliceId= 12;
    bobId= 23;
    charlieId= 34;

     fbId=123;
     twitterId=234;
     wikiId=345;
     cnnId=456;
     cnetIid=567;
     gizId=678;

    millis = System.currentTimeMillis();
    date = new java.sql.Date(millis);
    d = WebsiteImpl.getInstance();
    roleDao = RoleImpl.getInstance();
  }

  /**
   * INSERT Statements below
   */

  @Test
  public void stage_001_createDevelopersAndUsers() {

    Developer alice = new Developer("4321rewq",12, "Alice", "Wonder", "alice",
            "alice", "alice@wonder.com", null);
    Developer bob = new Developer( "5432trew", 23, "Bob", "Marley", "bob",
            "bob", "bob@marley.com", null);
    Developer charlie = new Developer("6543ytre",34, "Charles", "Garcia",
            "charlie", "charlie", "chuch@garcia.com",
            null);

    DeveloperDao d1 = DeveloperImpl.getInstance();
    d1.createDeveloper(alice);
    d1.createDeveloper(bob);
    d1.createDeveloper(charlie);

    User dan= new User(45,"Dan","Martin","dan","dan","dan@martin.com",null,null);
    User ed=new User(56,"Ed","Karaz","ed","ed","ed@kar.com",null,null);

    UserDao.getInstance().createUser(dan);
    UserDao.getInstance().createUser(ed);
  }


  @Test
  public void stage_002_createFacebook() {
    fb = new Website(123, "Facebook",
            "an online social media and social networking service", date, date, 1234234);
    WebsiteImpl.getInstance().createWebsiteForDeveloper(12, fb);
  }

  @Test
  public void stage_003_assignFB() {
    roleDao=RoleImpl.getInstance();
    roleDao.assignWebsiteRole(23, fbId, "editor");
    roleDao.assignWebsiteRole(34, fbId, "admin");
  }

  @Test
  public void stage_004_createTwitter() {
    twitter = new Website(234, "Twitter",
            "an online news and social networking service", date, date, 4321543);
    d=WebsiteImpl.getInstance();
    d.createWebsiteForDeveloper(12, twitter);

  }
  @Test
  public void stage_005_assignTwitter() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignWebsiteRole(23, twitterId, "editor");
    roleDao.assignWebsiteRole(34, twitterId, "admin");
  }
  @Test
  public void stage_006_createWIKI() {
    wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", date, date, 3456654);

    d=WebsiteImpl.getInstance();
    d.createWebsiteForDeveloper(charlieId, wikipedia);

  }
  @Test
  public void stage_007_assignWIKI() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignWebsiteRole(aliceId, wikiId, "editor");
    roleDao.assignWebsiteRole(bobId, wikiId, "admin");
  }
  @Test
  public void stage_008_createCNN() {
    cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", date, date, 6543345);

    d=WebsiteImpl.getInstance();
    d.createWebsiteForDeveloper(aliceId, cnn);
  }
  @Test
  public void stage_009_assignCNN() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignWebsiteRole(bobId, cnnId, "editor");
    roleDao.assignWebsiteRole(charlieId, cnnId, "admin");
  }

  @Test
  public void stage_010_createCNET() {
    cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", date, date, 5433455);

    d=WebsiteImpl.getInstance();

    d.createWebsiteForDeveloper(bobId, cnet);

  }
  @Test
  public void stage_011_assignCNET() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignWebsiteRole(charlieId, cnetIid, "editor");
    roleDao.assignWebsiteRole(aliceId, cnetIid, "admin");
  }

  @Test
  public void stage_012_creategiz() {
    gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", date, date, 4322345);
    d=WebsiteImpl.getInstance();
    d.createWebsiteForDeveloper(charlieId, gizmodo);
  }
  @Test
  public void stage_013_assigngiz() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignWebsiteRole(aliceId, gizId, "editor");
    roleDao.assignWebsiteRole(bobId, gizId, "admin");
  }

@Test
  public void stage_014_createPages1() {
  Page home_cnet = new Page(123, "Home", "Landing page", Date.valueOf("2019-09-04")
          , Date.valueOf("2019-10-20"), 123434);
  PageImpl.getInstance().createPageForWebsite(cnetIid, home_cnet);
}
  @Test
  public void stage_015_createPages2() {
    Page about_giz = new Page(234,"About","Website description",Date.valueOf("2019-09-04")
            ,Date.valueOf("2019-10-20"),234545);
    PageImpl.getInstance().createPageForWebsite(gizId, about_giz);
  }

  @Test
  public void stage_016_createPages3() {
    Page contact = new Page(345,"Contact","Addresses, phones, and contact info",Date.valueOf("2019-09-04")
            ,Date.valueOf("2019-10-20"),345656);
    PageImpl.getInstance().createPageForWebsite(wikiId, contact);
  }
  @Test
  public void stage_017_createPages4() {
    Page preferences = new Page(456,"Preferences",
            "Where users can configure their preferences",Date.valueOf("2019-09-04")
            ,Date.valueOf("2019-10-20"),456776);
    PageImpl.getInstance().createPageForWebsite(cnnId, preferences);
  }
  @Test
  public void stage_018_createPages5() {
    Page profile = new Page(567,"Profile","Users can configure their personal information",Date.valueOf("2019-09-04")
            ,Date.valueOf("2019-10-20"),567878);
    PageImpl.getInstance().createPageForWebsite(cnetIid, profile);
  }

  @Test
  public void stage_019_assignPages() {
    roleDao=RoleImpl.getInstance();

    roleDao.assignPageRole(aliceId,123,"editor");
    roleDao.assignPageRole(bobId,123,"reviewer");
    roleDao.assignPageRole(charlieId,123,"writer");

    roleDao.assignPageRole(bobId,234,"editor");
    roleDao.assignPageRole(charlieId,234,"reviewer");
    roleDao.assignPageRole(aliceId,234,"writer");

    roleDao.assignPageRole(charlieId,345,"editor");
    roleDao.assignPageRole(aliceId,345,"reviewer");
    roleDao.assignPageRole(bobId,345,"writer");

    roleDao.assignPageRole(aliceId,456,"editor");
    roleDao.assignPageRole(bobId,456,"reviewer");
    roleDao.assignPageRole(charlieId,456,"writer");

    roleDao.assignPageRole(bobId,567,"editor");
    roleDao.assignPageRole(charlieId,567,"reviewer");
    roleDao.assignPageRole(aliceId,567,"writer");
  }

  @Test
  public void stage_020_createWidget1() {
    Widget w = new HeadingWidget(123, "head123", null, null, null,
            null, "Welcome", 0, null, null, null, null,
            null, null, Dtype.HEADING);
    WidgetImpl.getInstance().createWidgetForPage(123, w);
  }

  @Test
  public void stage_021_createWidget2() {
    Widget w = new HtmlWidget(234, "post234", null, null, null,
            null, "<p>Lorem</p>", 0, null, null, null, null,
            null, null, Dtype.HTML);
    WidgetImpl.getInstance().createWidgetForPage(234, w);
  }

  @Test
  public void stage_022_createWidget3() {
    Widget w = new HeadingWidget(345, "head345", null, null, null,
            null, "Hi", 1, null, null, null, null,
            null, null, Dtype.HEADING);
    WidgetImpl.getInstance().createWidgetForPage(345, w);
  }

  @Test
  public void stage_023_createWidget4() {
    Widget w = new HtmlWidget(456, "intro456", null, null, null,
            null, "<h1>Hi</h1>", 2, null, null, null, null,
            null, null, Dtype.HTML);
    WidgetImpl.getInstance().createWidgetForPage(345, w);
  }

  @Test
  public void stage_024_createWidget5() {
    Widget w = new ImageWidget(567, "image345", 50, 100, null,
            null, null, 3, null, null, "/img/567.png", null,
            null, null, Dtype.IMAGE);
    WidgetImpl.getInstance().createWidgetForPage(345, w);
  }

  @Test
  public void stage_025_createWidget6() {
    Widget w = new YouTubeWidget(678, "video456", 400, 300, null,
            null, null, 0, null, null, null,
            "https://youtu.be/h67VX51QXiQl",
            null, null, Dtype.YOUTUBE);
    WidgetImpl.getInstance().createWidgetForPage(456, w);
  }

  }

