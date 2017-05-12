package com.iii.BB101.james_;

import java.sql.*;

// Query selected employees
public class QueryDemo5 {
	public static void main(String[] args) {
		
		String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");) {     
				//建立連線		//SQL指令	   //敘述連線
			String empno = "1001";
			
			String qryStmt = "SELECT * FROM employee WHERE empno=" + empno;
			Statement stmt = conn.createStatement();				
			ResultSet rs = stmt.executeQuery(qryStmt);
			
			String qryStmt2 = "SELECT * FROM employee WHERE empno=" + empno;
			PreparedStatement pstmt2 = conn.prepareStatement(qryStmt2);
			ResultSet rs2 = pstmt2.executeQuery();		
			
			
			String qryStmt3 = "SELECT * FROM employee WHERE empno=?";
			PreparedStatement pstmt3 = conn.prepareStatement(qryStmt3);
			pstmt3.setString(1, empno);
			ResultSet rs3 = pstmt3.executeQuery();
			
		 	String qryStmt4 = "SELECT * FROM employee WHERE deptno=? and title=?";
			PreparedStatement pstmt4 = conn.prepareStatement(qryStmt4);
			pstmt4.setString(1, "100");
			pstmt4.setString(2, "engineer");
			ResultSet rs4 = pstmt4.executeQuery();
			
			while(rs4.next()) {
				System.out.println("name = " + rs4.getString("ename"));
				System.out.println("salary = " + rs4.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}// end of main()
}// end of class QueryDemo5
