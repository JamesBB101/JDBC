package com.iii.bb101.james;

import java.sql.*;

public class DatabaseMetaDataDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			//取得資料庫相關資訊
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println(dbmd.getDatabaseProductName());//資料庫名稱
			System.out.println(dbmd.getDriverName());//驅動程式名稱
			ResultSet rs = dbmd.getTableTypes();//表格種類
			
			while(rs.next())
				System.out.print(rs.getString("TABLE_TYPE") + ", ");
			System.out.print("\n");
			
			rs = dbmd.getPrimaryKeys(null, null, "EMPLOYEE");//表格主鍵()
			while(rs.next()) {
				System.out.print(rs.getString("TABLE_NAME") + ", ");
				System.out.print(rs.getString("COLUMN_NAME") + ", ");
				System.out.print(rs.getString("KEY_SEQ"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}// end of main()
}// end of class DatabaseMetaDataDemo 
