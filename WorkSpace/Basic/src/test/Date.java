package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

	public static void main(String[] args) {
		java.sql.Date dateSql = new java.sql.Date(new java.util.Date().getTime());
		System.out.println(dateSql);
		System.out.println("*******************");
		java.sql.Date dateSql2 = new java.sql.Date(System.currentTimeMillis());
		System.out.println(dateSql2);
		System.out.println("*******************");
		java.util.Date dateUtil3 = new java.util.Date(System.currentTimeMillis());
		System.out.println(dateUtil3);
		System.out.println("*******************");
		
		java.util.Calendar CalToday = java.util.Calendar.getInstance(); //Calendar�غc�l����new
//����@�ȥ�Calendar (java.sql.Date & java.util.Date�j�hDeprecated)
					//�u�n��getInstance()�غc�l�q�`�O����new
		int year = CalToday.get(Calendar.YEAR);
		int month = CalToday.get(Calendar.MONTH)+1;
		int day = CalToday.get(Calendar.DATE);
		System.out.println(year + "/" + month + "/" + day);
		
		System.out.println("*******************");
		java.util.Date today = new java.util.Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
		
		System.out.println(sdf.format(today));

	}

}