package idv.david.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HouseDAOImpl implements HouseDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/TestDB?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO house(_id, case_t, district, case_f, location)"
			+ "VALUES(?, ?, ?, ?, ?)";
	
	@Override
	public void addAll(List<House> houseList) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");

			pstmt = con.prepareStatement(SQL);
			for (House house : houseList) {
				pstmt.setInt(1, house.get_id());
				pstmt.setString(2, house.getCase_t());
				pstmt.setString(3, house.getDistrict());
				pstmt.setString(4, house.getCase_f());
				pstmt.setString(5, house.getLocation());
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {

			// 依建立順序關閉資源 (越晚建立越早關閉)
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

	@Override
	public void add(House house) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<House> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<House> getByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

}
