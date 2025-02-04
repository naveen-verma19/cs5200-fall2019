package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;

public class AddressDoa {

  private static AddressDoa instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private AddressDoa() {
  }

  public static AddressDoa getInstance()  {
    if (instance == null)
      instance = new AddressDoa();
    return instance;
  }

  private String DELETE_ADDRESS = "DELETE FROM address \n" +
          "WHERE\n" +
          "    `primary` = ?\n" +
          "    AND person_id = (SELECT id FROM  person WHERE username = ?);";

  private String INSERT="INSERT INTO address(street1,street2,city,zip,`primary`,person_id) " +
          "VALUES(?,?,?,?,?,?);\n";

  public int deleteAddress(String username, boolean isPrimary) {
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE_ADDRESS);
      statement.setString(2,username);
      if(isPrimary)
        statement.setBoolean(1,true);
      else
        statement.setBoolean(1,false);
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
}
