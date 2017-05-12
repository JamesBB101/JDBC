package com.iii.bb101.james_homework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HW_2 {
	public static void main(String[] args) throws IOException {

		String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");) {

			String insStmt = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);

			String path = "./emp.txt";
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String row;
			int counter = 0;
			while ((row = br.readLine()) != null) {
				System.out.println(row);
				String[] row_array = row.split(",");					
				
				for (int i = 0; i < row_array.length-1; i++) {
					String str = row_array[i];
					System.out.println((i + 1)+","+ str);				
					pstmt.setString(i+1,row_array[i]);
					}
				
//					pstmt.setInt(1, Integer.valueOf(row_array[0].trim()));  //Integer.valueOf()  StringÂà«¬
//					pstmt.setString(2, row_array[1]);
//					pstmt.setString(3, row_array[2].trim());
//					pstmt.setDouble(4, Double.valueOf(row_array[3].trim()));
//					pstmt.setInt(5, Integer.valueOf(row_array[4].trim()));
//					pstmt.setString(6, row_array[5].trim());
					pstmt.addBatch();					
					
					counter++;										
					if (counter == 2) {
						pstmt.executeBatch();
						counter = 0;
					}
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
