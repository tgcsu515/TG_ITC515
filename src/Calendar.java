/** Author: Kanchan Bala, Team Name: Team Genius, Student id: 11635336
 *@version 9.0.4(build 9.0.4+ 11)
 *
 *In this program I have made few changes with variable name "cal". As per the guidelines, the names should be
 * meaningful, so I have changed "cal" to the "currentCalendar". Other than that I could not find any other errors 
 * which breach the guidelines.
 */

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;
	private static java.util.Calendar currentCalendar; 				//Author: Kanchan Bala, I have changed the variable name from cal to currentCalendar
	
	
	private Calendar() {
		currentCalendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		currentCalendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {
		try {
			currentCalendar.setTime(date);
	        currentCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        currentCalendar.set(java.util.Calendar.MINUTE, 0);  
	        currentCalendar.set(java.util.Calendar.SECOND, 0);  
	        currentCalendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	public synchronized Date Date() {
		try {
	        currentCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        currentCalendar.set(java.util.Calendar.MINUTE, 0);  
	        currentCalendar.set(java.util.Calendar.SECOND, 0);  
	        currentCalendar.set(java.util.Calendar.MILLISECOND, 0);
			return currentCalendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {
		Date now = Date();
		currentCalendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = currentCalendar.getTime();
		currentCalendar.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
