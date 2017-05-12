package idv.david.advanced;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestGeneratedKeys_PreparedStatement {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO EMP2(ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(?, ?, ?, ?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			String[] cols = { "EMPNO" }; // 或 int cols[] = {1};

			PreparedStatement pstmt = con.prepareStatement(SQL, cols);
			pstmt.setString(1, "DAVID");
			pstmt.setString(2, "MANAGER");

			// 利用GregorianCalendar指定日期再轉換成java.sql.Date資料
			GregorianCalendar cal = new GregorianCalendar(2016, Calendar.JANUARY, 1);
			Date date = new Date(cal.getTimeInMillis());
			pstmt.setDate(3, date);

			pstmt.setInt(4, 2500);
			pstmt.setDouble(5, 0.0);
			pstmt.setInt(6, 40);

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						String key = rs.getString(i);
						System.out.println("自增主鍵值 = " + key +"(剛新增成功的員工編號)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			
			rs.close();
			
			pstmt = con.prepareStatement("SELECT * FROM EMP2");
			ResultSet rs2 = pstmt.executeQuery();
			while (rs2.next()) {
				System.out.println("EMPNO = " + rs2.getInt(1));
				System.out.println("ENAME = " + rs2.getString(2));
				System.out.println("=================");
			}
			
			rs2.close();
			pstmt.close();
			
			
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {
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
