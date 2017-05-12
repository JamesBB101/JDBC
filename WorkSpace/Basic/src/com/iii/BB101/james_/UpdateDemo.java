package com.iii.BB101.james_;

import java.sql.*;

// Update employee data
public class UpdateDemo {
	public static void main(String[] args) {
		String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");) {

			String updStmt = "UPDATE employee SET salary=? WHERE empno=?";
			//update statement of sql 
			PreparedStatement pstmt = conn.prepareStatement(updStmt);//±Ô­z
			pstmt.setDouble(1, 58000);
			pstmt.setInt(2, 1009);
			int num = pstmt.executeUpdate();//°õ¦æ
			System.out.println("update count = " + num);

			
			
			String qryStmt = "SELECT * FROM employee WHERE empno=?";
			pstmt = conn.prepareStatement(qryStmt);
			pstmt.setInt(1, 1009);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of main()
}// end of class UpdateDemo
