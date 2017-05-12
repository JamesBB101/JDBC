package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_SELECT_ResultSet {
	private static final String URL = "jdbc:mysql://localhost:3306/HR?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM EMP ORDER BY SAL";   //降冪在salary後面加上DESC即可
//	private static final String SQL_ALIAS = "SELECT a.ENAME, a.SAL, a.JOB, b.DNAME FROM EMP a, DEPT b WHERE a.DEPTNO = b.DEPTNO";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			
			pstmt = con.prepareStatement(SQL);
//			pstmt = con.prepareStatement(SQL_ALIAS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ename = rs.getString("ENAME");
				double sal = rs.getDouble("SAL");
				String job = rs.getString("JOB");
//				String dname = rs.getString("DNAME");
				
				System.out.println("EMP NAME = " + ename);
				System.out.println("EMP SAL = " + sal);
				System.out.println("EMP JOB = " + job);
//				System.out.println("EMP DNAME = " + dname);
				System.out.println();
			}

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {

			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
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
