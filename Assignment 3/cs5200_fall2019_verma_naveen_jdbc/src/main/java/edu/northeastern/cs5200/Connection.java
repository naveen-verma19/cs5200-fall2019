package edu.northeastern.cs5200;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


 private static final String URL =
	"jdbc:mysql://cs5200-fall2019-verma.ckho0qdck994.us-east-1.rds.amazonaws.com/cs5200_jdbc";
		private static final String USER = "naveenverma088";
	private static final String PASSWORD = "asnobody19";


	private static java.sql.Connection dbConnection = null;

	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		if (dbConnection == null) {
			dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
			return dbConnection;
		} else {
			return dbConnection;
		}
	}

	public static void closeConnection() {
		try {
			if (dbConnection != null) {
				dbConnection.close();
				dbConnection = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
