package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * JDK8後已將ODBC驅動相關類別移除不再使用
 */
public class TestJDBC_ODBC {

	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException ce) {
			System.err.println(ce.getMessage());
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:odbc:HR");
			System.out.println("Connecting to database successfully! (連線成功！)");
			con.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

}
