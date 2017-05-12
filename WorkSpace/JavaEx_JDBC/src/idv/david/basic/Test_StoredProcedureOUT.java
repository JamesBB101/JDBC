package idv.david.basic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Test_StoredProcedureOUT {
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
			System.out.println("Connecting to database successfully! (連線成功！)");

			cstmt = con.prepareCall("{call get_by_salary_avg(?,?)}");
			cstmt.setDouble(1, 1000);
			cstmt.registerOutParameter(2, Types.DOUBLE);
			cstmt.execute();
			
			double avg = cstmt.getDouble(2);
			System.out.println("EMP salary below 1000 average is " + avg);
			
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
