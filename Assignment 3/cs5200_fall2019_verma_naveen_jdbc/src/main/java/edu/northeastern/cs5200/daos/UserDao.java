package edu.northeastern.cs5200.daos;

import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.User;

public class UserDao {
  private static UserDao instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private UserDao() {
    connection = null;
    statement = null;
  }

  public static UserDao getInstance() {
    if (instance == null)
      instance = new UserDao();
    return instance;
  }

  private String CREATE_PERSON = "INSERT INTO person(id,first_name,last_name,username,password,email) " +
          "VALUES('ID','FIRST_NAME','LAST_NAME','USERNAME','PASSWORD','EMAIL')";
  private String CREATE_USER = "INSERT INTO user(id) VALUES('ID')";


  public void createUser(User user) {
    try {
      connection = Connection.getConnection();
      statement = connection.createStatement();
      int id = user.getId();
      String SQL = CREATE_PERSON.replaceAll("ID", Integer.toString(id));
      SQL = SQL.replaceAll("FIRST_NAME", user.getFirstName());
      SQL = SQL.replaceAll("LAST_NAME", user.getLastName());
      SQL = SQL.replaceAll("USERNAME", user.getUsername());
      SQL = SQL.replaceAll("PASSWORD", user.getPassword());
      SQL = SQL.replaceAll("EMAIL", user.getEmail());
      statement.executeUpdate(SQL);

      SQL = CREATE_USER.replaceAll("ID", Integer.toString(id));
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
}
