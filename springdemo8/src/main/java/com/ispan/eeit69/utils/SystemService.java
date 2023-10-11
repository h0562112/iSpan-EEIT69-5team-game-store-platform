package com.ispan.eeit69.utils;

import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class SystemService {

	public static String EMPLOYEE_IMAGE_FILE_FOLDER = "C:\\SpringBootExample\\images\\emplpyee";
	
	public static String clobToString(Clob clob) throws SerialException, SQLException {
		if (clob == null) {
			return "";
		}
		return clob.getSubString(1, (int)clob.length());
	};
	
}
