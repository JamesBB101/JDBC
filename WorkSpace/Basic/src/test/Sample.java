package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
//static Connection	  getConnection(String url, String user, String passwd)
			String empno = "1001";
			String qryStmt = "SELECT * FROM employee WHERE empno=" + empno;
			String qryStmt3 = "SELECT * FROM employee WHERE empno=?";
			
			Statement stmt = conn.createStatement();
			PreparedStatement pstmt3 = conn.prepareStatement(qryStmt3);//extends Statement
			pstmt3.setString(1, empno);//�ʺAparameter
				//setType(int paraIndex, Type value)
			ResultSet rs2 = pstmt3.executeQuery();
//			int num = pstmt.executeUpdate();//����
//			System.out.println("update count = " + num);
			
			while(rs2.next()) {  //�C�����
				System.out.print("name = " + rs2.getString("ename") + ", ");
				System.out.println("salary = " + rs2.getDouble("salary"));
			}										//getType(columnName)
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
