package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.err.println(se.getMessage());
				}
			}
		}
	}
}
