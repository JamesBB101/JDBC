package idv.david.basic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test_INSERT_PreparedStatement {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");

			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, 7015);
			pstmt.setString(2, "DAVID");
			pstmt.setString(3, "MANAGER");

			// 利用GregorianCalendar指定日期再轉換成java.sql.Date資料
			GregorianCalendar cal = new GregorianCalendar(2016, Calendar.JANUARY, 1);
			Date date = new Date(cal.getTimeInMillis());
			pstmt.setDate(4, date);
			
			pstmt.setDouble(5, 2500);
			pstmt.setDouble(6, 0.0);
			pstmt.setInt(7, 40);

			int rowCount = pstmt.executeUpdate();
			System.out.println("新增 " + rowCount + " 筆資料");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {

			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

}
