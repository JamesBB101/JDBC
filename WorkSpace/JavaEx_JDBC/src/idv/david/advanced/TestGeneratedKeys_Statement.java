package idv.david.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestGeneratedKeys_Statement {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO EMP2(ENAME, JOB, HIREDATE, SAL, COMM, DEPTNO)"
			+ "VALUES('DAVID', 'MANAGER', '2016-01-01', 2500, 0.0, 40)";

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String[] cols = { "EMPNO" }; // 或是 int[] cols = {1};
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL, cols);
			
			ResultSet rs = stmt.getGeneratedKeys();
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
			stmt.close();
			
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM EMP2");
			while (rs2.next()) {
				System.out.println("EMPNO = " + rs2.getInt(1));
				System.out.println("ENAME = " + rs2.getString(2));
				System.out.println("=================");
			}
			
			rs2.close();
			stmt2.close();
			
			
		} catch (ClassNotFoundException ce) {
			System.err.println(ce.getMessage());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		} finally {
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
