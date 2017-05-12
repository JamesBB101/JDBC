package com.iii.bb101.james;

import java.sql.*;

public class DatabaseMetaDataDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			//���o��Ʈw������T
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println(dbmd.getDatabaseProductName());//��Ʈw�W��
			System.out.println(dbmd.getDriverName());//�X�ʵ{���W��
			ResultSet rs = dbmd.getTableTypes();//������
			
			while(rs.next())
				System.out.print(rs.getString("TABLE_TYPE") + ", ");
			System.out.print("\n");
			
			rs = dbmd.getPrimaryKeys(null, null, "EMPLOYEE");//���D��()
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
