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
import edu.northeastern.cs5200.models.Developer;

public class DeveloperImpl implements DeveloperDao {

  private static DeveloperImpl instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private DeveloperImpl() {
    connection = null;
    statement = null;
  }

  public static DeveloperImpl getInstance() {
    if (instance == null)
      instance = new DeveloperImpl();
    return instance;
  }

  private String CREATE_PERSON = "INSERT INTO person(id,first_name,last_name,username,password,email) " +
          "VALUES('ID','FIRST_NAME','LAST_NAME','USERNAME','PASSWORD','EMAIL')";
  private String CREATE_DEVELOPER = "INSERT INTO developer(id,developer_key) VALUES('ID','DEVELOPER_KEY')";

  private String DEVELOPER_JOINED_PERSON_VIEW = "SELECT p.* ,d.developer_key FROM \n" +
          "person p JOIN developer d\n" +
          "ON p.id=d.id";
  private String FIND_BY_USERNAME = "SELECT p.* ,d.developer_key FROM \n" +
          "person p JOIN developer d\n" +
          "ON p.id=d.id AND p.username='USERNAME'";
  private String FIND_BY_ID = "SELECT p.* ,d.developer_key FROM \n" +
          "person p JOIN developer d\n" +
          "ON p.id=d.id AND p.id='ID'";
  private String FIND_BY_CREDENTIALS = "SELECT p.* ,d.developer_key FROM \n" +
          "person p JOIN developer d\n" +
          "ON p.id=d.id and p.username='USERNAME' and p.password='PASSWORD'";
  private String UPDATE_DEVELOPER_PERSON_PREP = "UPDATE person \n" +
          "SET \n" +
          "first_name=?,\n" +
          "last_name=?,\n" +
          "username=?,\n" +
          "password=?,\n" +
          "email=?,\n" +
          "dob=?\n" +
          "WHERE id= ?";
  private String UPDATE_DEVELOPER_PREP = "UPDATE developer \n" +
          "SET \n" +
          "developer_key=?\n" +
          "WHERE id= ?";
  private String DELETE_DEVELOPER_FROM_PERSON="delete from person where id=";


  @Override
  public void createDeveloper(Developer developer) {
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      int id = developer.getId();
      String SQL = CREATE_PERSON.replaceAll("ID", Integer.toString(id));
      SQL = SQL.replaceAll("FIRST_NAME", developer.getFirstName());
      SQL = SQL.replaceAll("LAST_NAME", developer.getLastName());
      SQL = SQL.replaceAll("USERNAME", developer.getUsername());
      SQL = SQL.replaceAll("PASSWORD", developer.getPassword());
      SQL = SQL.replaceAll("EMAIL", developer.getEmail());
      statement.executeUpdate(SQL);

      SQL = CREATE_DEVELOPER.replaceAll("ID", Integer.toString(id));
      SQL = SQL.replaceAll("DEVELOPER_KEY", developer.getDeveloperKey());
      statement.executeUpdate(SQL);

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
  public Collection<Developer> findAllDevelopers() {
    List<Developer> developers = new ArrayList<Developer>();
    ResultSet results = null;
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      results = statement
              .executeQuery(DEVELOPER_JOINED_PERSON_VIEW);
      while (results.next()) {
        Developer developer = extractFromResult(results);
        developers.add(developer);
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
    return developers;
  }

  @Override
  public Developer findDeveloperById(int developerId) {
    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = FIND_BY_ID.replaceAll("ID", Integer.toString(developerId));
      result = statement
              .executeQuery(SQL);
      if (result.next()) {
        Developer res = extractFromResult(result);
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
  public Developer findDeveloperByUsername(String username) {
    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = FIND_BY_USERNAME.replaceAll("USERNAME", username);
      result = statement
              .executeQuery(SQL);
      if (result.next()) {
        Developer res = extractFromResult(result);
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
  public Developer findDeveloperByCredentials(String username, String password) {
    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = FIND_BY_USERNAME.replaceAll("USERNAME", username);
      SQL = SQL.replaceAll("PASSWORD", password);
      result = statement
              .executeQuery(SQL);
      if (result.next()) {
        Developer res = extractFromResult(result);
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
    public int updateDeveloper(int developerId, Developer developer) {
      try {
        connection = Connection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_DEVELOPER_PERSON_PREP);
        int id =  developerId;
        statement.setInt(7, id);
        statement.setString(1, developer.getFirstName());
        statement.setString(2, developer.getLastName());
        statement.setString(3, developer.getUsername());
        statement.setString(4, developer.getPassword());
        statement.setString(5, developer.getEmail());
        statement.setDate(6, developer.getDob());
        statement.executeUpdate();


        statement = connection.prepareStatement(UPDATE_DEVELOPER_PREP);
        statement.setInt(2, id);
        statement.setString(1, developer.getDeveloperKey());
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
  public int deleteDeveloper(int developerId) {
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      String SQL = DELETE_DEVELOPER_FROM_PERSON+developerId;
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

  private Developer extractFromResult(ResultSet results) throws SQLException {
    int id = results.getInt("id");
    String firstName = results.getString("first_name");
    String lastName = results.getString("last_name");
    String username = results.getString("username");
    String password = results.getString("password");
    String email = results.getString("email");
    Date dob = results.getDate("dob");
    String dkey = results.getString("developer_key");

    return new Developer(dkey, id, firstName, lastName, username, password, email, dob);
  }
}
