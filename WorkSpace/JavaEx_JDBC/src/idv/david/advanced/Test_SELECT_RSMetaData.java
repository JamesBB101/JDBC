package idv.david.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test_SELECT_RSMetaData {
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM EMP";
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			
			int numberOfColumns = rsmd.getColumnCount();
			for (int i = 1; i <= numberOfColumns; i++) {
				String colName = rsmd.getColumnName(i);
				String tableName = rsmd.getTableName(i);
				String name = rsmd.getColumnTypeName(i);
				boolean caseSen = rsmd.isCaseSensitive(i);
				boolean writable = rsmd.isWritable(i);
				System.out.println("Information for column " + colName);
				System.out.println("    Column is in table " + tableName);
				System.out.println("    DBMS name for type is " + name);
				System.out.println("    Is case sensitive:  " + caseSen);
				System.out.println("    Is possibly writable:  " + writable);
				System.out.println();
			}
			
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					String s = rs.getString(i);
					System.out.print(s + "  ");
				}
				System.out.println();
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} 
		
	}

}
