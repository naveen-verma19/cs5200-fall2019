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
import edu.northeastern.cs5200.models.Website;

public class WebsiteImpl implements WebsiteDao {
  private static WebsiteImpl instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private WebsiteImpl() {
  }

  public static WebsiteImpl getInstance()  {
    if (instance == null)
      instance = new WebsiteImpl();
    return instance;
  }

  private String CREATE_WEBSITE = "INSERT INTO website(id,`name`,description,visits) " +
          "VALUES(?,?,?,?)";


  private String FIND_WEBSITES = "SELECT * FROM website";

  private String FIND_WEBSITE_DEVELOPER="select * from website where id in (select website_id from website_role where developer_id=?)";

  private String FIND_WEBSITE_BY_ID="select * from website where id=";

  private String UPDATE_WEBSITE_PREP = "UPDATE website \n" +
          "SET \n" +
          "name=?,\n" +
          "description=?,\n" +
          "created=?,\n" +
          "updated=?,\n" +
          "visits=?\n" +
          "WHERE id= ?";
  @Override
  public void createWebsiteForDeveloper(int developerId, Website website) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE);

      int id = website.getId();
      statement.setInt(1, id);
      statement.setString(2, website.getName());
      statement.setString(3, website.getDescription());
      statement.setInt(4, website.getVisits());
      statement.executeUpdate();

      RoleImpl.getInstance().assignWebsiteRole(developerId,id,"owner");

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }

  }

  @Override
  public Collection<Website> findAllWebsites() {
    List<Website> websites = new ArrayList<Website>();

    ResultSet results = null;

    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      results = statement
              .executeQuery(FIND_WEBSITES);
      while (results.next()) {
        Website website = extractFromResult(results);
        websites.add(website);
      }

      results.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return websites;
  }

  @Override
  public Collection<Website> findWebsitesForDeveloper(int developerId) {
    List<Website> websites = new ArrayList<Website>();

    ResultSet results = null;

    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_WEBSITE_DEVELOPER);
      statement.setInt(1,developerId);
      results = statement
              .executeQuery();
      while (results.next()) {
        Website website = extractFromResult(results);
        websites.add(website);
      }
      results.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    finally {
      Connection.closeConnection();
    }
    return websites;
  }

  @Override
  public Website findWebsiteById(int websiteId) {
    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = FIND_WEBSITE_BY_ID+websiteId;
      result = statement
              .executeQuery(SQL);
      if (result.next()) {
        Website res = extractFromResult(result);
        return res;
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return null;
  }

  @Override
  public int updateWebsite(int websiteId, Website website) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE_WEBSITE_PREP);
      statement.setInt(6, websiteId);
      statement.setString(1, website.getName());
      statement.setString(2, website.getDescription());
      statement.setDate(3, website.getCreated());
      statement.setDate(4, website.getUpdated());
      statement.setInt(5, website.getVisits());
      return statement.executeUpdate();


    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return -1;

  }

  @Override
  public int deleteWebsite(int websiteId) {
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = "DELETE FROM website where id="+websiteId;
      return statement.executeUpdate(SQL);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return -1;
  }

  private Website extractFromResult(ResultSet results) throws SQLException {
    int id = results.getInt("id");
    String name = results.getString("name");
    String description = results.getString("description");
    Date created = results.getDate("created");
    Date updated = results.getDate("updated");
    int visits = results.getInt("visits");
    return new Website(id,name,description,created,updated,visits);
  }
}
