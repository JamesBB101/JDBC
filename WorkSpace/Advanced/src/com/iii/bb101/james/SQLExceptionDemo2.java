package com.iii.bb101.james;

import java.sql.*;

public class SQLExceptionDemo2 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		 String DatabaseProductName = "";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			
			DatabaseMetaData dbmd = conn.getMetaData();
			DatabaseProductName = dbmd.getDatabaseProductName();
			String qryStmt = "SELECT * FROM employe"; // error here
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("name = " + rs.getString("ename"));
				System.out.printf("salary =" + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			System.out.println("Vendor code : " + e.getErrorCode());
			System.out.println(DatabaseProductName);
			switch(DatabaseProductName){
				case "MySQL" :
					if(e.getErrorCode()==1146){
						System.out.println("columnName doesn't exist");
					}
				case "SQL Server" :
					if(e.getErrorCode()==1234){
						System.out.println("columnName doesn't exist");
					}
				case "Oracle" :
					if(e.getErrorCode()==5678){
						System.out.println("columnName doesn't exist");
					}
				default :
					System.out.println("Cann't defind");
			}
		} 
	}// end of main()
}// end of class SQLExceptionDemo
