package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;

public class PhoneDao {

  private static PhoneDao instance = null;

  private java.sql.Connection connection;
  private Statement statement;

  private PhoneDao() {
    connection = null;
    statement = null;
  }

  public static PhoneDao getInstance() {
    if (instance == null)
      instance = new PhoneDao();
    return instance;
  }

  public int updatePhone(int developerId, boolean isPrimary, String newNumber) {

    String UPDATE_PHONE = "UPDATE phone \n" +
            "SET \n" +
            "phone=?\n"+
            "WHERE person_id=? AND `primary`=?";
    try {
      connection = Connection.getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE_PHONE);

      statement.setString(1,newNumber);
      statement.setInt(2,developerId);

      if(isPrimary)
        statement.setBoolean(3,true);
      else
        statement.setBoolean(3,false);

      return statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

}
