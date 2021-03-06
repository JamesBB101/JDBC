package com.iii.bb101.james_homework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class HW_1 {

	public static void main(String[] args) throws IOException {
		
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {

			String qryStmt = "SELECT * FROM employee";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(qryStmt);

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			BufferedWriter bw = 
					new BufferedWriter(new FileWriter("./hw1.dat"));			
			StringBuffer sb = new StringBuffer();
			
			while (rs.next()) {				
				for (int i = 1; i <= count; i++) {					
					System.out.print(rs.getString(i) + ", ");
//					String row = rs.getString(i) + ", ";
//					byte[] byte_row = row.getBytes();
//					fout.write(byte_row);  //FileOutputStream
					sb.append(rs.getString(i) + ", ");
				}		
				sb.append("\r\n");	
				
				System.out.println();
			}
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
