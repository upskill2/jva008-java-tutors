package com.luxoft.jva008.module01;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SystemCurrentTimeTutor {

	public static void main(String[] args) {
		SystemCurrentTimeTutor systemClass = new SystemCurrentTimeTutor();
		log(systemClass.getTimeInMillis());
	}

	/**
	 * getTimeInMillis() Must return the current time in milliseconds
	 */
	public long getTimeInMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * The profiler should calculate how many milliseconds it took
	 * Running the Runnable.run () method
	 *
	 * @param run
	 * @return
	 */
	public long profiler(Runnable run) {
		// TODO your implementation goes here
		long start = System.currentTimeMillis();
		run.run();
		long end = System.currentTimeMillis();

		return end-start;
	}

	/**
	 * The method must return the date in milliseconds
	 */
	public Date getDate(long timeInMillis) {
		// TODO your implementation goes here

		Date date = new Date(timeInMillis);

		return date;
	}

	/**
	 * The method must return the date to which the plusDays are added (or subtracted)
	 */
	public Date getDatePlus(Date date, int plusDays) {
		// TODO your implementation goes here

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, plusDays);

		return calendar.getTime();
	}

	@Test
	public void testGetDate() {
		Date date = getDate(1363877852603l);
		log(date.toString());
		assertEquals(date.getTime(), 1363877852603l);
		Date dateOfBeginning = getDate(0);
		log(dateOfBeginning.toString());
		assertEquals(dateOfBeginning.getTime(), 0);
	}

	@Test
	public void testGetDatePlus() {
		// create date for 1.04.2013, 12:30
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 3, 1, 12, 30, 0);
		cal.clear(Calendar.MILLISECOND);
		// create date for 3.04.2013, 12:30
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2013, 3, 3, 12, 30, 0);
		cal2.clear(Calendar.MILLISECOND);
		Date datePlus = getDatePlus(cal.getTime(), 2);
		log(cal.getTime().toString());
		log(datePlus.toString());
		log(cal2.getTime().toString());
		log(datePlus.getTime() + ":" + cal2.getTimeInMillis());
		assertEquals("datePlus() Returns an incorrect date",
				datePlus, cal2.getTime());
	}

	@Test
	public void testGetTimeInMillis() {
		assertTrue(
				"getTimeInMillis() Must return time in milliseconds",
				getTimeInMillis() > 1363877852603l);
	}

	@Test
	public void testForProfiler() {
		assertTrue(noOperationProfiler() == 0);
		assertTrue(forProfiler() > 0);
	}

	public long noOperationProfiler() {
		return profiler(new Runnable() {
			public void run() {
			}
		});
	}

	public long forProfiler() {
		return profiler(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000000; i++) ;
			}
		});
	}

}
