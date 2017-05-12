package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_INSERT_Statement {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(7015, 'DAVID', 'MANAGER', '2016-01-01', 2500, 0.0, 40)";

	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");

			stat = con.createStatement();
			int rowCount = stat.executeUpdate(SQL);
			System.out.println("新增 " + rowCount + " 筆資料");

		} catch (ClassNotFoundException ce) {
			System.err.println(ce);
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		} finally {

			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException se) {
					System.err.println(se.getMessage());
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.err.println(se.getMessage());
				}
			}
		}
	}
}
