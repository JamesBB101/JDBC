package idv.david.rowset;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class TestWriteXML {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/HR";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT * FROM EMP";

	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			WebRowSet rowset = RowSetProvider.newFactory().createWebRowSet();
			rowset.setUrl(URL);
			rowset.setUsername(USER);
			rowset.setPassword(PASSWORD);
			rowset.setCommand(SQL);
			rowset.execute();

			BufferedWriter bw = new BufferedWriter(new FileWriter("Output/Emp.xml"));
			rowset.writeXml(bw); // 或是System.out
			bw.flush();
			bw.close();
			
			System.out.println("寫出成功！");

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} 
	}

}
