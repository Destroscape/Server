package com;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateAndTime {

	public static String getTodaysDate() {
		Calendar date = new GregorianCalendar();
		return date.get(Calendar.DAY_OF_MONTH) + "."
				+ (date.get(Calendar.MONTH) + 1) + "."
				+ date.get(Calendar.YEAR);
	}

}