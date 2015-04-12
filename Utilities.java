package CIS350;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * The class where the dates are manipulated for information needed.
 * @author  Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 */
public class Utilities {

	/**
	 * Calculates the number of days between the give date, i, and today's date.
	 * @param i a given GregorianCalendar date
	 * @return the difference of days as an int
	 */

	public static int CurrentDateComp(final GregorianCalendar i) {
		String m = "";
		String d = "";
		String y = "";
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		m = String.valueOf(calendar.get(GregorianCalendar.MONTH));
		d = String.valueOf(calendar.get(GregorianCalendar.DAY_OF_MONTH));
		y = String.valueOf(calendar.get(GregorianCalendar.YEAR));
		String total = m + "/" + d + "/" + y;
		GregorianCalendar currentDate = strToGregCalendar(total);
		int days = daysLapsed(i, currentDate);

		return days;
	}

	/**
	 * Makes a GregorianCalendar date into a string.
	 * @param i is the date being changed.
	 * @return returns null if it is empty or the date in string form.
	 */
	public static String gToString(final GregorianCalendar i) {
		if (i == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(i.getTime());
	}

	/**
	 * Figures the amount of days between 2 GregorianCalendar dates.
	 * @param c1 is the first GregorianCalendar date
	 * @param c2 is the second GregorialCalendar date
	 * @return returns the number of days
	 */
	public static int daysLapsed(final GregorianCalendar c1, 
			final GregorianCalendar c2) {
		int elapsed = 0, factor = 1;
		GregorianCalendar gc1, gc2;
		if (c2.after(c1)) {
			gc1 = (GregorianCalendar) c1.clone();
			gc2 = (GregorianCalendar) c2.clone();
		} else {
			gc1 = (GregorianCalendar) c2.clone();
			gc2 = (GregorianCalendar) c1.clone();
			factor = -1;
		}
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);

		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed * factor;
	}

	/**
	 * Makes a String date into a GregorianCalendar date.
	 * @param i is the string being changed.
	 * @return returns the date in GregorianCalendar form
	 */
	public static GregorianCalendar strToGregCalendar(final String i) {
		if (i == null) {
			return null;
		}
		GregorianCalendar g = new GregorianCalendar();
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse(i);	
			g.setTime(date);
		} catch (ParseException ex) {
			return null;
		}
		return g;
	}

	/**
	 * Finds out if the first GregorianCalendar comes before the second.
	 * @param c1 is the first GregorianCalendar date
	 * @param c2 is the second GregorianCalendar date
	 * @return returns true of it comes before.
	 */
	public static boolean beforeAfter(final GregorianCalendar c1, 
			final GregorianCalendar c2) {
		if (c1.after(c2)) {
			return false;
		}
		return true;
	}
}
