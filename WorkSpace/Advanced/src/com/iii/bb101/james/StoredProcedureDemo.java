package com.iii.bb101.james;

import java.sql.*;

// Update employee data with CallableStatement
public class StoredProcedureDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			
			CallableStatement cstmt = conn.prepareCall("{call upd_emp_salary(?, ?)}");
//CallableStatement cstmt = conn.prepareCall("{call procedureName(parament)}");
//write function(stored procedure)�w�s�{��  in SQL
//then call Stored procedure in Java
			cstmt.setDouble(1, 44000);
			cstmt.setInt(2, 1002);
			cstmt.execute(); /********execute**********/
			
			cstmt =  conn.prepareCall("{call qry_emp(?,?,?)}");
//CallableStatement cstmt = conn.prepareCall("{call procedureName(parament)}");
			cstmt.setInt(1, 1002);						 //IN
			cstmt.registerOutParameter(2,Types.VARCHAR); //OUT
			cstmt.registerOutParameter(3,Types.DOUBLE);  //OUT   SQL Type
			cstmt.execute();
			String ename = cstmt.getString(2);  //get
			double salary = cstmt.getDouble(3); //java Type
			
			System.out.print("name = " + ename + ", ");
			System.out.println("salary = " + salary);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
//******Stored Procedure******
//
//CREATE PROCEDURE upd_emp_salary (
//		IN p_salary	decimal(10,2),
//		IN p_empno 	decimal(4)
//	)
//	BEGIN
//		UPDATE employee SET salary = p_salary WHERE empno = p_empno;
//	END
//CREATE PROCEDURE qry_emp (
//		IN 	p_empno		decimal(4),
//		OUT p_ename		varchar(30),
//		OUT p_salary	decimal(10,2)
//	)
//	BEGIN
//		SELECT ename, salary INTO p_ename, p_salary FROM employee WHERE empno = p_empno;
//	END


