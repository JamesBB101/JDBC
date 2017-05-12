package com.iii.BB101.james_;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class GetLoadedDriverNames {

	public static void main(String[] args) {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		//取得已載入的Drivers
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println(driver.getClass().getName());
		}
	}
}
