package idv.david.connectionpool;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class TestSampleDataSource {

	public static void main(String[] args) {
		try {
			// 該程式碼片段日後將由Application Server設定，就無須再寫
			///////////////////////////////////////////
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName("localhost");
			ds.setPort(3306);
			ds.setDatabaseName("HR");
			ds.setUser("root");
			ds.setPassword("123456");
			//////////////////////////////////////////
			
			startRegistry();
			javax.naming.Context ctx = createContext();
			ctx.rebind("jdbc/HR", ds); // 使用JNDI命名服務登記
			
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMP");
			while (rs.next()) {
				System.out.println("EMPNO = " + rs.getInt(1));
				System.out.println("ENAME = " + rs.getString(2));
				System.out.println("===============");
			}
			
			rs.close();
			stmt.close();
			con.close();
			ctx.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}
	
	// 參考用
	private static void startRegistry() throws RemoteException {
		LocateRegistry.createRegistry(1099);
		System.out.println("RMI registry ready.");
	}
	
	// 參考用
	private static InitialContext createContext() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}
}
