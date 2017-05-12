package idv.david.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TestSavePoint {
	private static final String URL = "jdbc:mysql://localhost:3306/HR?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		Savepoint savePoint = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			
			con.setAutoCommit(false);
			
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7015, 'DAVID', 'MANAGER', '2016-01-01', 2500, 0.0, 40)");
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7016, 'JAMES', 'CLERK', '2016-02-02', 800, 0.0, 20)");
			
			savePoint = con.setSavepoint();
			
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7017, 'VINCENT', 'SALESMAN', '2016-03-03', 600, 1000.0, 30)");
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7018, 'RON', 'ANALYST', '2016-04-04', 3500, 0.0, 10)");
			
			con.commit();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			try {
				con.rollback(savePoint);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			if (con != null) {
				try {
					con.setAutoCommit(true);
					// 記得釋放儲存點
					con.releaseSavepoint(savePoint);
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

}
