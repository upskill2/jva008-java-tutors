package com.luxoft.jva008.module02;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import org.junit.Test;

public class FormatDateTutor {
	
	/**
	 * Returns date in format dd.mm.yy
	 * Use Formatter
	 */
	public String getDateByFormatter(Date date) {

		Formatter f = new Formatter();

		String res = String.valueOf(f.format("%td.%tm.%ty", date, date, date));


		return res;
	}

	/**
	 * Returns date in format "27 of May, 2015"
	 * Use Formatter
	 */
	public String getDateString(Date date) {

		Formatter f = new Formatter();

	String res = String.valueOf(f.format("%td of %tB, %tY",date, date, date));

		return res;
	}
	
	/**
	 * Returns date in format "01.05.13"
	 * Use SimpleDateFormat
	 */
	public String getDateBySimpleDateFormat(Date date) {

		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yy");

		return simpleFormat.format(date);
	}
	
	/**
	 * Returns date of type Date, converted from the line in format dd.mm.yy
	 * Use SimpleDateFormat, method parse()
	 */
	public Date parseDDMMYY(String s) {

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");

		Date d = null;

		try{
			d=format.parse(s);
		}
		catch (ParseException e){
			e.printStackTrace();
		}


		return d;
	}
	
	@Test
	public void testFormatDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 4, 1); // 1st of May, 2013
		Date date = cal.getTime();
		
		String dateByFormatter = getDateByFormatter(date);
		log("dateByFormatter: " + dateByFormatter);
		assertEquals(dateByFormatter, "01.05.13");
		
		String dateBySimpleDateFormat = getDateBySimpleDateFormat(date);
		log("dateBySimpleDateFormat: " + dateBySimpleDateFormat);
		assertEquals(dateBySimpleDateFormat, "01.05.13"); //this

		System.out.println(getDateString(new Date()));
	}
	
	@Test
	public void testParseDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 4, 1, 0, 0, 0); // 1st of May, 2013
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		Date d = parseDDMMYY("01.05.13");
		assertEquals(date, d);
	}

}
