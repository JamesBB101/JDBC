package com.iii.BB101.james_;

import java.sql.*;

// delete a employee data
public class DeleteDemo {
	public static void main(String[] args) {
		String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");) {
			
			String delStmt = "DELETE FROM employee WHERE empno=?";
			PreparedStatement pstmt = conn.prepareStatement(delStmt);
			pstmt.setInt(1, 1009);
			int num = pstmt.executeUpdate();
			System.out.println("delete count = " + num);
			
			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}// end of main()
}// end of class DeleteDemo
