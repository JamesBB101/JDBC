package idv.david.rowset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Test_UPDATE_CachedRowSet {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM USER WHERE ID = ?";

	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			CachedRowSet rowset = RowSetProvider.newFactory().createCachedRowSet();
			rowset.setUrl(URL);
			rowset.setUsername(USER);
			rowset.setPassword(PASSWORD);
			rowset.setCommand(SQL);
			rowset.setInt(1, 3);
			// 執行Query，會自動連線，之後再斷線
			rowset.execute();

			while (rowset.next()) {
				rowset.updateString("PASSWORD", "123456");
				rowset.updateRow();
	         }

			// 需先將Auto Commit關閉
			con.setAutoCommit(false);
			// 將rowset物件送進資料庫
			rowset.acceptChanges(con);
			// 設定成功 commit
			con.commit();
			System.out.println("更新成功！");

			con.setAutoCommit(true);
			con.close();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
