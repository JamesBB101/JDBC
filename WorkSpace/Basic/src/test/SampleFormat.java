package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SampleFormat {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
