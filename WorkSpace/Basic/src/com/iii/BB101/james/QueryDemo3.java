package com.iii.BB101.james;

import java.sql.*;

// getType() practice 
/*
	empno		decimal(4)			PRIMARY KEY,
	empname		varchar(30)			NOT NULL,
	hiredate	datetime			NOT NULL,
	salary		decimal(10,2)		NOT NULL,
*/
public class QueryDemo3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");
			
			String qryStmt = "SELECT * FROM employee";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(qryStmt);
			
			while(rs.next()) {
			
//				int empno = rs.getInt("empno");
//				String ename = rs.getString("ename");
//				java.util.Date hiredate = rs.getDate("hiredate"); // 2004-12-25
//				double salary =  rs.getDouble("salary");  // 56000.0
				
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				String hiredate = rs.getString("hiredate"); // 2004-12-25 00:00:00.0
				String salary =  rs.getString("salary");  // 56000.00
				
			/*	Object empno = rs.getObject("empno");
				Object ename = rs.getObject("ename");
				Object hiredate = rs.getObject("hiredate"); // 2004-12-25 00:00:00.0
				Object salary =  rs.getObject("salary");  // 56000.00
				
				int empno = rs.getInt(1);
				String ename = rs.getString(2);
			//	java.util.Date hiredate = rs.getDate(3); // 2004-12-25
				java.sql.Date hiredate = rs.getDate(3); // 2004-12-25, java.sql.Date extends java.util.Date
				double salary =  rs.getDouble(4);  // 56000.0
			*/	
				System.out.println("emp no = " + empno);
				System.out.println("name = " + ename);
				System.out.println("hire date = " + hiredate);
				System.out.println("salary = " + salary);
				System.out.println("----------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class QueryDemo3
