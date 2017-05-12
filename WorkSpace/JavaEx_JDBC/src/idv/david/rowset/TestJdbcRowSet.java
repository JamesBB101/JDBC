package idv.david.rowset;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class TestJdbcRowSet {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM EMP";

	public static void main(String[] args) {
		JdbcRowSet rowset = null;
		try {
			Class.forName(DRIVER);
			rowset = RowSetProvider.newFactory().createJdbcRowSet();
			rowset.setUrl(URL);
			rowset.setUsername(USER);
			rowset.setPassword(PASSWORD);
			rowset.setCommand(SQL);
			// 執行Query，會自動連線，不會斷線
			rowset.execute();

			rowset.beforeFirst();
			while (rowset.next()) {
				System.out.println("EMPNO = " + rowset.getInt(1));
				System.out.println("ENAME = " + rowset.getString(2));
				System.out.println("==============");
			}

			rowset.moveToInsertRow();
			rowset.updateInt(1, 7015);
			rowset.updateString(2, "DAVID");
			rowset.updateString(3, "MANAGER");

			// 利用GregorianCalendar指定日期再轉換成java.sql.Date資料
			GregorianCalendar cal = new GregorianCalendar(2016, Calendar.JANUARY, 1);
			Date date = new Date(cal.getTimeInMillis());
			rowset.updateDate(4, date);
			rowset.updateDouble(5, 2500);
			rowset.updateDouble(6, 0.0);
			rowset.updateInt(7, 40);

			rowset.insertRow();

			System.out.println("================Query again!!!==================");

			rowset.beforeFirst();
			while (rowset.next()) {
				System.out.println("EMPNO = " + rowset.getInt(1));
				System.out.println("ENAME = " + rowset.getString(2));
				System.out.println("==============");
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rowset != null) {
				try {
					rowset.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

}
