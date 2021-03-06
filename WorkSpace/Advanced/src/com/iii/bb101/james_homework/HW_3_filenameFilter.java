package com.iii.bb101.james_homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HW_3_filenameFilter {

	public static void main(String[] args) throws IOException {
		File file = new File("./res");
		String[] images = file.list(new FilenameFilter() {	//��file��������
			public boolean accept(File f, String fname) {
				return fname.toLowerCase().endsWith(".jpg");
			}
		});

		FileInputStream fis = null;
		
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			
			String insertStmt = "UPDATE emp SET photo=? WHERE empno=?";
			PreparedStatement ppstmt = conn.prepareStatement(insertStmt);
			int count = 0 ;
			for(String s : images){
				fis = new FileInputStream(file + "/" + s);
				int empno = Integer.valueOf(s.substring(0, 4));
				ppstmt.setBinaryStream(1, fis,file.length());
				ppstmt.setInt(2, empno);
				ppstmt.addBatch();
				count++;
				if(count==3){
					int[] a = ppstmt.executeBatch();
					System.out.println("Insert photn successfully count:" + a.length);

					count = 0;
				}
			}
			int[] a = ppstmt.executeBatch();
			System.out.println("Insert photn successfully count:" + a.length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
//Use anonymous class as a filter to get jpg, jpeg and webp files
//new FilenameFilter(){
//    public boolean accept(File f, String fname){
//        return fname.toLowerCase().endsWith(".jpg") || fname.toLowerCase().endsWith(".jpeg") || fname.toLowerCase().endsWith(".webp");
//    }
//});