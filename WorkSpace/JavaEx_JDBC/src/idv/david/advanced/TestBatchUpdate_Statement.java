package idv.david.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBatchUpdate_Statement {
	// MySQL URL需加上後面參數
	private static final String URL = "jdbc:mysql://localhost:3306/HR?rewriteBatchedStatements=true";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			stat = con.createStatement();
			stat.addBatch("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(7015, 'DAVID', 'MANAGER', '2016-01-01', 2500, 0.0, 40)");
			stat.addBatch("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(7016, 'JAMES', 'CLERK', '2016-02-02', 800, 0.0, 20)");
			stat.addBatch("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(7017, 'VINCENT', 'SALESMAN', '2016-03-03', 600, 1000.0, 30)");
			stat.addBatch("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES(7018, 'RON', 'ANALYST', '2016-04-04', 3500, 0.0, 10)");
			stat.executeBatch();
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (stat != null) {
				try {
					stat.close();
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
