package idv.david.advanced;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *  MySQL Use following data types as per your need
 *
 *	TINYBLOB   :     maximum length of 255 bytes  
 *	BLOB       :     maximum length of 65,535 bytes  
 *	MEDIUMBLOB :     maximum length of 16,777,215 bytes  
 *	LONGBLOB   :     maximum length of 4,294,967,295 bytes  
 * 	
 *  遇到無法新增時
 *  
 *  1. 開啟Workbench > Server > Options File > Networking > max_allowed_packet > 改成500M
 *	2. 開啟Workbench > Server > Options File > InnoDB > innodb_log_file_size > 改成256M
 * 
 */
public class TestWriteBlob {
	private static final String URL = "jdbc:mysql://localhost:3306/CLUB?useUnicode=true&amp;characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO CLUB(ID, NAME, PIC)" + "VALUES(?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. setBlob
			pstmt.setInt(1, 1);
			pstmt.setString(2, "拜仁慕尼黑");
			Blob blob = con.createBlob();
			byte[] pic2 = getPictureByteArray("items/FC_Bayern_München.png");
			blob.setBytes(1, pic2);
			pstmt.setBlob(3, blob);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 2. setBytes
			pstmt.setInt(1, 2);
			pstmt.setString(2, "巴塞隆納");
			byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
			pstmt.setBytes(3, pic);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 3. setBinaryStream
			pstmt.setInt(1, 3);
			pstmt.setString(2, "皇家馬德里");
			InputStream is = getPictureStream("items/FC_Real_Madrid.png");
			pstmt.setBinaryStream(3, is, is.available());
			pstmt.executeUpdate();

			System.out.println("新增成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
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

	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
