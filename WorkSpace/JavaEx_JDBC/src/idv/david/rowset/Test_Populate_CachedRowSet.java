package idv.david.rowset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Test_Populate_CachedRowSet {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM EMP";

	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			CachedRowSet rowset = RowSetProvider.newFactory().createCachedRowSet();
			// 將查詢出來的ResultSet資料封裝在CachedRowSet實體裡
			rowset.populate(rs);

			rs.close();
			stmt.close();
			con.close(); // 【注意: 資料庫連線已在此關閉】

			while (rowset.next()) {
				int empno = rowset.getInt(1);
				String ename = rowset.getString(2);

				System.out.print(" EMPNO = " + empno);
				System.out.print(" ENAME = " + ename);
				System.out.print("\n");
			}

			System.out.println("-------------------------------------");

			while (rowset.previous()) {
				int empno = rowset.getInt(1);
				String ename = rowset.getString(2);

				System.out.print(" EMPNO = " + empno);
				System.out.print(" ENAME = " + ename);
				System.out.print("\n");
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
