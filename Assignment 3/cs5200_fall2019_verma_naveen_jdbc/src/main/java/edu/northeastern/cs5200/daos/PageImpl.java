package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

public class PageImpl implements PageDao {

  private static PageImpl instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private PageImpl() {
    connection = null;
    statement = null;
  }

  public static PageImpl getInstance() {
    if (instance == null)
      instance = new PageImpl();
    return instance;
  }

  private String CREATE_PAGE = "INSERT INTO page(id,`title`,description, created, updated, views, website_id) " +
          "VALUES(?,?,?,?,?,?,?)";

  private String CREATE_ROLE = "INSERT INTO website_role(developer_id,website_id,role) " +
          "VALUES(?,?,?)";

  private String FIND_PAGE = "SELECT * FROM page";

  private String FIND_WEBSITE_DEVELOPER = "select * from website where id in (select website_id from website_role where developer_id=?)";

  private String FIND_PAGE_BY_ID = "select * from page where id=";

  private String UPDATE_PAGE_PREP = "UPDATE page \n" +
          "SET \n" +
          "title=?,\n" +
          "description=?,\n" +
          "created=?,\n" +
          "updated=?,\n" +
          "views=?,\n" +
          "website_id=?\n" +
          "WHERE id= ?";

  @Override
  public void createPageForWebsite(int websiteId, Page page) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_PAGE);

      int id = page.getId();
      statement.setInt(1, id);
      statement.setString(2, page.getTitle());
      statement.setString(3, page.getDescription());
      statement.setDate(4, page.getUpdated());
      statement.setDate(5, page.getCreated());
      statement.setInt(6, page.getViews());
      statement.setInt(7, websiteId);
      statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }

  }

  @Override
  public Collection<Page> findAllPages() {
    List<Page> pages = new ArrayList<Page>();
    List<Integer> web_ids = new ArrayList<Integer>();

    ResultSet results = null;

    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      results = statement
              .executeQuery(FIND_PAGE);
      while (results.next()) {
        Page page = extractFromResult(results);
        pages.add(page);
        web_ids.add(results.getInt("website_id"));
      }
      results.close();

      for (int i = 0; i < pages.size(); i++) {
        int web_id = web_ids.get(i);
        pages.get(i).setWebsite(WebsiteImpl.getInstance().findWebsiteById(web_id));
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }
    return pages;
  }

  @Override
  public Page findPageById(int pageId) {
    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = FIND_PAGE + " where id=" + pageId;
      result = statement
              .executeQuery(SQL);
      if (result.next()) {
        Page res = extractFromResult(result);
        int web_id = result.getInt("website_id");
        res.setWebsite(WebsiteImpl.getInstance().findWebsiteById(web_id));
        return res;
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }
    return null;
  }

  @Override
  public Collection<Page> findPagesForWebsite(int websiteId) {
    List<Page> pages = new ArrayList<Page>();
    List<Integer> web_ids = new ArrayList<Integer>();

    ResultSet results = null;

    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      results = statement
              .executeQuery(FIND_PAGE + " WHERE website_id=" + websiteId);
      while (results.next()) {
        Page page = extractFromResult(results);
        pages.add(page);
        web_ids.add(results.getInt("website_id"));
      }

      for (int i = 0; i < pages.size(); i++) {
        int web_id = web_ids.get(i);
        pages.get(i).setWebsite(WebsiteImpl.getInstance().findWebsiteById(web_id));
      }

      results.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }
    return pages;
  }

  @Override
  public int updatePage(int pageId, Page page) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE_PAGE_PREP);
      statement.setInt(7, pageId);
      statement.setString(1, page.getTitle());
      statement.setString(2, page.getDescription());
      statement.setDate(3, page.getCreated());
      statement.setDate(4, page.getUpdated());
      statement.setInt(5, page.getViews());
      statement.setInt(6, page.getWebsite().getId());

      return statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }
    return -1;

  }

  @Override
  public int deletePage(int pageId) {
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = "DELETE FROM page where id=" + pageId;
      return statement.executeUpdate(SQL);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Connection.closeConnection();
    }
    return -1;
  }

  private Page extractFromResult(ResultSet results) throws SQLException, ClassNotFoundException {
    int id = results.getInt("id");
    String title = results.getString("title");
    String description = results.getString("description");
    Date created = results.getDate("created");
    Date updated = results.getDate("updated");
    int views = results.getInt("views");

    Page p = new Page(id, title, description, created, updated, views);
    p.setWebsite(null);
    return p;
  }
}
