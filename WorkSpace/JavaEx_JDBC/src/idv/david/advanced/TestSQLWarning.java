package idv.david.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class TestSQLWarning {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT ENAME, SAL FROM EMP ORDER BY ENAME";

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			System.out.println("insert之前===============================================");
			while (rs.next()) {
				String ename = rs.getString("ENAME");
				double sal = rs.getDouble("SAL");
				System.out.println("\t" + ename + "   " + sal);
				// 捕捉警告 (但是Oracle不支援)
				SQLWarning stmt_Warning = stmt.getWarnings();
				printWarning(stmt_Warning);
				// 捕捉警告 (但是Oracle不支援)
				SQLWarning rs_Warning = rs.getWarnings();
				printWarning(rs_Warning);
			}
			System.out.println();

			con.setAutoCommit(false);
			// insert資料
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7015, 'DAVID', 'MANAGER', '2016-01-01', 2500, 0.0, 40)");
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7016, 'JAMES', 'CLERK', '2016-02-02', 800, 0.0, 20)");
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7017, 'VINCENT', 'SALESMAN', '2016-03-03', 600, 1000.0, 30)");
			stmt.executeUpdate("INSERT INTO EMP(EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
					+ "VALUES(7018, 'RON', 'ANALYST', '2016-04-04', 3500, 0.0, 50)");

			con.commit();
			con.setAutoCommit(true);

			stmt.close();
			con.close();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			SQLException gen = new SQLException("SQL 操作已經取消", "S1008", 0);
            se.setNextException(gen); // Append the old exception
			while (se != null) {
				System.err.println("-------------SQLException caught-------------");
				System.err.println("Message: " + se.getMessage()); // 回傳String
				System.err.println("SQL state: " + se.getSQLState()); // 回傳String
				System.err.println("Vendor code: " + se.getErrorCode()); // 回傳int
				se = se.getNextException();
			}

			if (con != null) {
				try {
					System.err.print("-------Transaction is being rolled back------ ");
					con.rollback(); // 設定於當有exception發生時之catch區塊內
				} catch (SQLException excep) {
					System.err.print("SQLException: ");
					System.err.println(excep.getMessage());
				}
			}
		}
	}

	private static boolean printWarning(SQLWarning warning) throws SQLException {
		if (warning == null)
			return false;
		while (warning != null) {
			System.err.println("\n----Warning----");
			System.err.println("Message: " + warning.getMessage());
			System.err.println("SQL state: " + warning.getSQLState());
			System.err.println("Vendor code: " + warning.getErrorCode());
			System.out.println("---------------");
			warning = warning.getNextWarning();
			System.out.println();
		}
		return true;
	}

}
