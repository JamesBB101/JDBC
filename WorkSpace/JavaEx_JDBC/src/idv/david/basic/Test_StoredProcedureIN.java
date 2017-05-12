package idv.david.basic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_StoredProcedureIN {
	private static final String URL = "jdbc:mysql://localhost:3306/HR?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			cstmt = con.prepareCall("{call get_by_salary(?)}");
			cstmt.setDouble(1, 1000);

			rs = cstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("ENAME");
				String job = rs.getString("JOB");
				double sal = rs.getDouble("SAL");

				System.out.println("EMP Name = " + name);
				System.out.println("EMP JOB = " + job);
				System.out.println("EMP SAL = " + sal);
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

			if (cstmt != null) {
				try {
					cstmt.close();
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
