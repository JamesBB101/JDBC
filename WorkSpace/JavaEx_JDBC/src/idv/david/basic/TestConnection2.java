package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection2 {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		// JDK7以後
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("Connecting to database successfully! (連線成功！)");
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} 
	}
}
