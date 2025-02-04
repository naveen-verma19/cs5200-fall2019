package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Website;

public class RoleImpl implements RoleDao {

  private static RoleImpl instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private RoleImpl() {
  }

  public static RoleImpl getInstance() {
    if (instance == null)
      instance = new RoleImpl();
    return instance;
  }

  private String CREATE_WEBSITE_ROLE = "INSERT INTO website_role(developer_id,website_id,role) " +
          "VALUES(?,?,?)";
  private String CREATE_PAGE_ROLE = "INSERT INTO page_role(developer_id,page_id,role) " +
          "VALUES(?,?,?)";

  private String DELETE_WEBSITE_ROLE = "DELETE FROM website_role where " +
          "developer_id=? " +
          "AND website_id=?";
  private String DELETE_PAGE_ROLE = "DELETE FROM page_role where " +
          "developer_id=? " +
          "AND page_id=?";


  public String getWebsiteRole(int developerId, int websiteId) {

    try {
      String getRoleStr="select * from website_role where developer_id=? and website_id=?";
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(getRoleStr);
      statement.setInt(1, developerId);
      statement.setInt(2, websiteId);

      ResultSet result = null;
      result = statement
              .executeQuery();
      if (result.next()) {
        String role= result.getString("role");
        return role;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return null;
  }

  public String getPageRole(int developerId, int page_id) {
    try{
      String getRoleStr="select * from page_role where developer_id=? and page_id=?";
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(getRoleStr);
      statement.setInt(1, developerId);
      statement.setInt(2, page_id);

      ResultSet result = null;
      result = statement
              .executeQuery();
      if (result.next()) {
        String role= result.getString("role");
        return role;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return null;
  }
  @Override
  public void assignWebsiteRole(int developerId, int websiteId, String role) {
    try {
        connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE_ROLE);
      statement.setInt(1, developerId);
      statement.setInt(2, websiteId);
      statement.setString(3, role);

      PriviledgeImpl.getInstance().assignWebsitePrivilege(developerId, websiteId, "read");
      if (role.equals("owner") || role.equals("admin") || role.equals("writer") || role.equals("editor"))
        PriviledgeImpl.getInstance().assignWebsitePrivilege(developerId, websiteId, "update");

      if (role.equals("owner") || role.equals("admin") || role.equals("writer"))
        PriviledgeImpl.getInstance().assignWebsitePrivilege(developerId, websiteId, "create");

      if (role.equals("owner") || role.equals("admin"))
        PriviledgeImpl.getInstance().assignWebsitePrivilege(developerId, websiteId, "delete");

      statement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void assignPageRole(int developerId, int pageId, String role) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_PAGE_ROLE);
      statement.setInt(1, developerId);
      statement.setInt(2, pageId);
      statement.setString(3, role);

      PriviledgeImpl.getInstance().assignPagePriviledge(developerId, pageId, "read");
      if (role.equals("owner") || role.equals("admin") || role.equals("writer") || role.equals("editor"))
        PriviledgeImpl.getInstance().assignPagePriviledge(developerId, pageId, "update");

      if (role.equals("owner") || role.equals("admin") || role.equals("writer"))
        PriviledgeImpl.getInstance().assignPagePriviledge(developerId, pageId, "create");

      if (role.equals("owner") || role.equals("admin"))
        PriviledgeImpl.getInstance().assignPagePriviledge(developerId, pageId, "delete");

      statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteWebsiteRole(int developerId, int websiteId, String role) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
      statement.setInt(1, developerId);
      statement.setInt(2, websiteId);
      //delete (did,wid)
      PriviledgeImpl.getInstance().deleteWebsitePriviledge(developerId, websiteId, "update");
      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
  }

  @Override
  public void deletePageRole(int developerId, int pageId, String role) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_PAGE_ROLE);
      statement.setInt(1, developerId);
      statement.setInt(2, pageId);
      //delete (did,wid)
      PriviledgeImpl.getInstance().deletePagePriviledge(developerId, pageId, "update");
      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
  }

}
