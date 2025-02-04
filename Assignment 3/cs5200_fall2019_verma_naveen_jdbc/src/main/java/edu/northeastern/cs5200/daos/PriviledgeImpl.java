package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;

public class PriviledgeImpl implements PriviledgeDao {
  private static PriviledgeImpl instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private PriviledgeImpl() {
    connection = null;
    statement = null;
  }

  public static PriviledgeImpl getInstance() {
    if (instance == null)
      instance = new PriviledgeImpl();
    return instance;
  }

  private String CREATE_WEBSITE_PRIVILEDGE="INSERT INTO website_priviledge(developer_id,website_id,priviledge) " +
          "VALUES(?,?,?)";

  private String CREATE_PAGE_PRIVILEDGE="INSERT INTO page_priviledge(developer_id,page_id,priviledge) " +
          "VALUES(?,?,?)";
  private String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM website_priviledge where " +
          "developer_id=? " +
          "AND website_id=?";

  private String DELETE_PAGE_PRIVILEDGE = "DELETE FROM page_priviledge where " +
          "developer_id=? " +
          "AND page_id=?";

  @Override
  public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_WEBSITE_PRIVILEDGE);
      statement.setInt(1, developerId);
      statement.setInt(2, websiteId);
      statement.setString(3,priviledge);
      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE_PAGE_PRIVILEDGE);
      statement.setInt(1, developerId);
      statement.setInt(2, pageId);
      statement.setString(3,priviledge);

      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
      statement.setInt(1, developerId);
      statement.setInt(2, websiteId);
      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
      statement.setInt(1, developerId);
      statement.setInt(2, pageId);
      statement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
